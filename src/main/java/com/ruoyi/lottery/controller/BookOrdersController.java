package com.ruoyi.lottery.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lottery.domain.BookOrders;
import com.ruoyi.lottery.service.IBookOrdersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售订单Controller
 * 
 * @author Shawn
 * @date 2024-02-29
 */
@Controller
@RequestMapping("/lottery/orders")
public class BookOrdersController extends BaseController
{
    private String prefix = "lottery/orders";

    @Autowired
    private IBookOrdersService bookOrdersService;

    @RequiresPermissions("lottery:orders:view")
    @GetMapping()
    public String orders()
    {
        return prefix + "/orders";
    }

    /**
     * 查询销售订单列表
     */
    @RequiresPermissions("lottery:orders:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookOrders bookOrders)
    {
        startPage();
        List<BookOrders> list = bookOrdersService.selectBookOrdersList(bookOrders);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @RequiresPermissions("lottery:orders:export")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookOrders bookOrders)
    {
        List<BookOrders> list = bookOrdersService.selectBookOrdersList(bookOrders);
        ExcelUtil<BookOrders> util = new ExcelUtil<BookOrders>(BookOrders.class);
        return util.exportExcel(list, "销售订单数据");
    }

    /**
     * 新增销售订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存销售订单
     */
    @RequiresPermissions("lottery:orders:add")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookOrders bookOrders)
    {
        return toAjax(bookOrdersService.insertBookOrders(bookOrders));
    }

    /**
     * 修改销售订单
     */
    @RequiresPermissions("lottery:orders:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookOrders bookOrders = bookOrdersService.selectBookOrdersById(id);
        mmap.put("bookOrders", bookOrders);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售订单
     */
    @RequiresPermissions("lottery:orders:edit")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookOrders bookOrders)
    {
        return toAjax(bookOrdersService.updateBookOrders(bookOrders));
    }

    /**
     * 删除销售订单
     */
    @RequiresPermissions("lottery:orders:remove")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookOrdersService.deleteBookOrdersByIds(ids));
    }
}

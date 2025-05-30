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
import com.ruoyi.lottery.domain.BookOrderdetails;
import com.ruoyi.lottery.service.IBookOrderdetailsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单明细Controller
 * 
 * @author Shawn
 * @date 2024-02-29
 */
@Controller
@RequestMapping("/lottery/orderdetails")
public class BookOrderdetailsController extends BaseController
{
    private String prefix = "lottery/orderdetails";

    @Autowired
    private IBookOrderdetailsService bookOrderdetailsService;

    @RequiresPermissions("lottery:orderdetails:view")
    @GetMapping()
    public String orderdetails()
    {
        return prefix + "/orderdetails";
    }

    /**
     * 查询订单明细列表
     */
    @RequiresPermissions("lottery:orderdetails:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookOrderdetails bookOrderdetails)
    {
        startPage();
        List<BookOrderdetails> list = bookOrderdetailsService.selectBookOrderdetailsList(bookOrderdetails);
        return getDataTable(list);
    }

    /**
     * 导出订单明细列表
     */
    @RequiresPermissions("lottery:orderdetails:export")
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookOrderdetails bookOrderdetails)
    {
        List<BookOrderdetails> list = bookOrderdetailsService.selectBookOrderdetailsList(bookOrderdetails);
        ExcelUtil<BookOrderdetails> util = new ExcelUtil<BookOrderdetails>(BookOrderdetails.class);
        return util.exportExcel(list, "订单明细数据");
    }

    /**
     * 新增订单明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单明细
     */
    @RequiresPermissions("lottery:orderdetails:add")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookOrderdetails bookOrderdetails)
    {
        return toAjax(bookOrderdetailsService.insertBookOrderdetails(bookOrderdetails));
    }

    /**
     * 修改订单明细
     */
    @RequiresPermissions("lottery:orderdetails:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookOrderdetails bookOrderdetails = bookOrderdetailsService.selectBookOrderdetailsById(id);
        mmap.put("bookOrderdetails", bookOrderdetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单明细
     */
    @RequiresPermissions("lottery:orderdetails:edit")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookOrderdetails bookOrderdetails)
    {
        return toAjax(bookOrderdetailsService.updateBookOrderdetails(bookOrderdetails));
    }

    /**
     * 删除订单明细
     */
    @RequiresPermissions("lottery:orderdetails:remove")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookOrderdetailsService.deleteBookOrderdetailsByIds(ids));
    }
}

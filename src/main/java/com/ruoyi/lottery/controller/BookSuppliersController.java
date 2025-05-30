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
import com.ruoyi.lottery.domain.BookSuppliers;
import com.ruoyi.lottery.service.IBookSuppliersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购商Controller
 * 
 * @author Shawn
 * @date 2024-02-29
 */
@Controller
@RequestMapping("/lottery/suppliers")
public class BookSuppliersController extends BaseController
{
    private String prefix = "lottery/suppliers";

    @Autowired
    private IBookSuppliersService bookSuppliersService;

    @RequiresPermissions("lottery:suppliers:view")
    @GetMapping()
    public String suppliers()
    {
        return prefix + "/suppliers";
    }

    /**
     * 查询采购商列表
     */
    @RequiresPermissions("lottery:suppliers:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookSuppliers bookSuppliers)
    {
        startPage();
        List<BookSuppliers> list = bookSuppliersService.selectBookSuppliersList(bookSuppliers);
        return getDataTable(list);
    }

    /**
     * 导出采购商列表
     */
    @RequiresPermissions("lottery:suppliers:export")
    @Log(title = "采购商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookSuppliers bookSuppliers)
    {
        List<BookSuppliers> list = bookSuppliersService.selectBookSuppliersList(bookSuppliers);
        ExcelUtil<BookSuppliers> util = new ExcelUtil<BookSuppliers>(BookSuppliers.class);
        return util.exportExcel(list, "采购商数据");
    }

    /**
     * 新增采购商
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购商
     */
    @RequiresPermissions("lottery:suppliers:add")
    @Log(title = "采购商", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookSuppliers bookSuppliers)
    {
        return toAjax(bookSuppliersService.insertBookSuppliers(bookSuppliers));
    }

    /**
     * 修改采购商
     */
    @RequiresPermissions("lottery:suppliers:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookSuppliers bookSuppliers = bookSuppliersService.selectBookSuppliersById(id);
        mmap.put("bookSuppliers", bookSuppliers);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购商
     */
    @RequiresPermissions("lottery:suppliers:edit")
    @Log(title = "采购商", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookSuppliers bookSuppliers)
    {
        return toAjax(bookSuppliersService.updateBookSuppliers(bookSuppliers));
    }

    /**
     * 删除采购商
     */
    @RequiresPermissions("lottery:suppliers:remove")
    @Log(title = "采购商", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookSuppliersService.deleteBookSuppliersByIds(ids));
    }
}

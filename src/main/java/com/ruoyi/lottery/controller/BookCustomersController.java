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
import com.ruoyi.lottery.domain.BookCustomers;
import com.ruoyi.lottery.service.IBookCustomersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户Controller
 * 
 * @author Shawn
 * @date 2024-02-29
 */
@Controller
@RequestMapping("/lottery/customers")
public class BookCustomersController extends BaseController
{
    private String prefix = "lottery/customers";

    @Autowired
    private IBookCustomersService bookCustomersService;

    @RequiresPermissions("lottery:customers:view")
    @GetMapping()
    public String customers()
    {
        return prefix + "/customers";
    }

    /**
     * 查询客户列表
     */
    @RequiresPermissions("lottery:customers:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookCustomers bookCustomers)
    {
        startPage();
        List<BookCustomers> list = bookCustomersService.selectBookCustomersList(bookCustomers);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @RequiresPermissions("lottery:customers:export")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookCustomers bookCustomers)
    {
        List<BookCustomers> list = bookCustomersService.selectBookCustomersList(bookCustomers);
        ExcelUtil<BookCustomers> util = new ExcelUtil<BookCustomers>(BookCustomers.class);
        return util.exportExcel(list, "客户数据");
    }

    /**
     * 新增客户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("lottery:customers:add")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookCustomers bookCustomers)
    {
        return toAjax(bookCustomersService.insertBookCustomers(bookCustomers));
    }

    /**
     * 修改客户
     */
    @RequiresPermissions("lottery:customers:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookCustomers bookCustomers = bookCustomersService.selectBookCustomersById(id);
        mmap.put("bookCustomers", bookCustomers);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户
     */
    @RequiresPermissions("lottery:customers:edit")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookCustomers bookCustomers)
    {
        return toAjax(bookCustomersService.updateBookCustomers(bookCustomers));
    }

    /**
     * 删除客户
     */
    @RequiresPermissions("lottery:customers:remove")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookCustomersService.deleteBookCustomersByIds(ids));
    }
}

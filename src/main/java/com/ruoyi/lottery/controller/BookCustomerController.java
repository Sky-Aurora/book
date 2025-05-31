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
import com.ruoyi.lottery.domain.BookCustomer;
import com.ruoyi.lottery.service.IBookCustomerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户管理Controller
 *
 */
@Controller
@RequestMapping("/lottery/customer")
public class BookCustomerController extends BaseController
{
    private String prefix = "lottery/customer";

    @Autowired
    private IBookCustomerService bookCustomerService;

    @RequiresPermissions("lottery:customer:view")
    @GetMapping()
    public String customer()
    {
        return prefix + "/customer";
    }

    /**
     * 查询客户管理列表
     */
    @RequiresPermissions("lottery:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookCustomer bookCustomer)
    {
        startPage();
        List<BookCustomer> list = bookCustomerService.selectBookCustomerList(bookCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @RequiresPermissions("lottery:customer:export")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookCustomer bookCustomer)
    {
        List<BookCustomer> list = bookCustomerService.selectBookCustomerList(bookCustomer);
        ExcelUtil<BookCustomer> util = new ExcelUtil<BookCustomer>(BookCustomer.class);
        return util.exportExcel(list, "客户管理数据");
    }

    /**
     * 新增客户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户管理
     */
    @RequiresPermissions("lottery:customer:add")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookCustomer bookCustomer)
    {
        return toAjax(bookCustomerService.insertBookCustomer(bookCustomer));
    }

    /**
     * 修改客户管理
     */
    @RequiresPermissions("lottery:customer:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookCustomer bookCustomer = bookCustomerService.selectBookCustomerById(id);
        mmap.put("bookCustomer", bookCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户管理
     */
    @RequiresPermissions("lottery:customer:edit")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookCustomer bookCustomer)
    {
        return toAjax(bookCustomerService.updateBookCustomer(bookCustomer));
    }

    /**
     * 删除客户管理
     */
    @RequiresPermissions("lottery:customer:remove")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookCustomerService.deleteBookCustomerByIds(ids));
    }
}

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
import com.ruoyi.lottery.domain.BookBooks;
import com.ruoyi.lottery.service.IBookBooksService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图书管理Controller
 * 
 * @author Shawn
 * @date 2024-03-04
 */
@Controller
@RequestMapping("/lottery/books")
public class BookBooksController extends BaseController
{
    private String prefix = "lottery/books";

    @Autowired
    private IBookBooksService bookBooksService;

    @RequiresPermissions("lottery:books:view")
    @GetMapping()
    public String books()
    {
        return prefix + "/books";
    }

    /**
     * 查询图书管理列表
     */
    @RequiresPermissions("lottery:books:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookBooks bookBooks)
    {
        startPage();
        List<BookBooks> list = bookBooksService.selectBookBooksList(bookBooks);
        return getDataTable(list);
    }

    /**
     * 导出图书管理列表
     */
    @RequiresPermissions("lottery:books:export")
    @Log(title = "图书管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookBooks bookBooks)
    {
        List<BookBooks> list = bookBooksService.selectBookBooksList(bookBooks);
        ExcelUtil<BookBooks> util = new ExcelUtil<BookBooks>(BookBooks.class);
        return util.exportExcel(list, "图书管理数据");
    }

    /**
     * 新增图书管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存图书管理
     */
    @RequiresPermissions("lottery:books:add")
    @Log(title = "图书管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookBooks bookBooks)
    {
        return toAjax(bookBooksService.insertBookBooks(bookBooks));
    }

    /**
     * 修改图书管理
     */
    @RequiresPermissions("lottery:books:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookBooks bookBooks = bookBooksService.selectBookBooksById(id);
        mmap.put("bookBooks", bookBooks);
        return prefix + "/edit";
    }

    /**
     * 修改保存图书管理
     */
    @RequiresPermissions("lottery:books:edit")
    @Log(title = "图书管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookBooks bookBooks)
    {
        return toAjax(bookBooksService.updateBookBooks(bookBooks));
    }

    /**
     * 删除图书管理
     */
    @RequiresPermissions("lottery:books:remove")
    @Log(title = "图书管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookBooksService.deleteBookBooksByIds(ids));
    }
}

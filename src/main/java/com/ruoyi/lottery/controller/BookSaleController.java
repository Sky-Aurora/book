package com.ruoyi.lottery.controller;

import java.util.List;

import com.ruoyi.lottery.domain.BookBooks;
import com.ruoyi.lottery.service.IBookBooksService;
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
import com.ruoyi.lottery.domain.BookSale;
import com.ruoyi.lottery.service.IBookSaleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售记录Controller
 * 
 * @author Shawn
 * @date 2024-03-05
 */
@Controller
@RequestMapping("/lottery/sale")
public class BookSaleController extends BaseController
{
    private String prefix = "lottery/sale";

    @Autowired
    private IBookSaleService bookSaleService;

    @RequiresPermissions("lottery:sale:view")
    @GetMapping()
    public String sale()
    {
        return prefix + "/sale";
    }

    /**
     * 查询销售记录列表
     */
    @RequiresPermissions("lottery:sale:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookSale bookSale)
    {
        startPage();
        List<BookSale> list = bookSaleService.selectBookSaleList(bookSale);
        return getDataTable(list);
    }

    /**
     * 导出销售记录列表
     */
    @RequiresPermissions("lottery:sale:export")
    @Log(title = "销售记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookSale bookSale)
    {
        List<BookSale> list = bookSaleService.selectBookSaleList(bookSale);
        ExcelUtil<BookSale> util = new ExcelUtil<BookSale>(BookSale.class);
        return util.exportExcel(list, "销售记录数据");
    }

    /**
     * 新增销售记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    @Autowired
    IBookBooksService bookBooksService;

    /**
     * 新增保存销售记录
     */
    @RequiresPermissions("lottery:sale:add")
    @Log(title = "销售记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookSale bookSale)
    {
        BookBooks book = bookBooksService.selectBookBooksById(bookSale.getBookId());
        if (book.getAmount() == 0){
            return error("该书已经没有库存！");
        }else{
            book.setAmount(book.getAmount()-1);
            bookBooksService.updateBookBooks(book);
        }
        return toAjax(bookSaleService.insertBookSale(bookSale));
    }

    /**
     * 修改销售记录
     */
    @RequiresPermissions("lottery:sale:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookSale bookSale = bookSaleService.selectBookSaleById(id);
        mmap.put("bookSale", bookSale);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售记录
     */
    @RequiresPermissions("lottery:sale:edit")
    @Log(title = "销售记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookSale bookSale)
    {
        return toAjax(bookSaleService.updateBookSale(bookSale));
    }

    /**
     * 删除销售记录
     */
    @RequiresPermissions("lottery:sale:remove")
    @Log(title = "销售记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //删除销售记录，增库存
        String id[] = ids.split(",");
        for (String s : id) {
            BookSale sale = bookSaleService.selectBookSaleById(Long.valueOf(s));
            BookBooks book = bookBooksService.selectBookBooksById(sale.getBookId());
            book.setAmount(book.getAmount()+1);
            bookBooksService.updateBookBooks(book);
        }

        return toAjax(bookSaleService.deleteBookSaleByIds(ids));
    }
}

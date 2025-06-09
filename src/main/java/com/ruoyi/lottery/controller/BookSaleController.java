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
     * 该方法用于记录一本书的销售信息，包括验证书籍库存、扣减库存和记录销售详情
     *
     * @param bookSale 销售记录对象，包含书籍ID和销售相关信息
     * @return 返回操作结果，包括成功或错误信息
     */
    @RequiresPermissions("lottery:sale:add")
    @Log(title = "销售记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookSale bookSale)
    {
        // 根据书籍ID查询书籍信息
        BookBooks book = bookBooksService.selectBookBooksById(bookSale.getBookId());

        // 检查书籍库存是否为0，如果为0则返回错误信息
        if (book.getAmount() == 0){
            return error("该书已经没有库存！");
        }else{
            // 如果有库存，则扣减书籍库存
            book.setAmount(book.getAmount()-1);
            bookBooksService.updateBookBooks(book);
        }

        // 插入销售记录并返回操作结果
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
    @RequiresPermissions("lottery:sale:remove") // 表明该方法需要特定的权限才能执行
    @Log(title = "销售记录", businessType = BusinessType.DELETE) // 记录操作日志，指定操作类型为删除
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        // 删除销售记录，并将与之关联的图书库存增加
        // 将传入的ID字符串分割成ID数组
        String id[] = ids.split(",");
        for (String s : id) {
            // 根据ID获取销售记录
            BookSale sale = bookSaleService.selectBookSaleById(Long.valueOf(s));
            // 获取销售记录关联的图书信息
            BookBooks book = bookBooksService.selectBookBooksById(sale.getBookId());
            // 将图书库存增加1
            book.setAmount(book.getAmount()+1);
            // 更新图书信息
            bookBooksService.updateBookBooks(book);
        }

        // 调用服务层方法，根据传入的ID删除销售记录
        return toAjax(bookSaleService.deleteBookSaleByIds(ids));
    }

}

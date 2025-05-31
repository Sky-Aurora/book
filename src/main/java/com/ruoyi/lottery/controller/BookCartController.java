package com.ruoyi.lottery.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.lottery.domain.BookBooks;
import com.ruoyi.lottery.domain.BookCustomer;
import com.ruoyi.lottery.domain.BookHistory;
import com.ruoyi.lottery.service.IBookBooksService;
import com.ruoyi.lottery.service.IBookCustomerService;
import com.ruoyi.lottery.service.IBookHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lottery.domain.BookCart;
import com.ruoyi.lottery.service.IBookCartService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 进货管理Controller
 *
 */
@Controller
@RequestMapping("/lottery/cart")
public class BookCartController extends BaseController
{
    private String prefix = "lottery/cart";

    @Autowired
    private IBookCartService bookCartService;

    @Autowired
    private IBookBooksService bookBooksService;

    @Autowired
    private IBookCustomerService customerService;

    @RequiresPermissions("lottery:cart:view")
    @GetMapping()
    public String cart(Model model)
    {
        model.addAttribute("customers",customerService.selectBookCustomerList(new BookCustomer()));
        model.addAttribute("books",bookBooksService.selectBookBooksList(new BookBooks()));
        return prefix + "/cart";
    }

    /**
     * 查询进货管理列表
     */
    @RequiresPermissions("lottery:cart:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookCart bookCart)
    {
        startPage();
        List<BookCart> list = bookCartService.selectBookCartList(bookCart);
        return getDataTable(list);
    }

    /**
     * 导出进货管理列表
     */
    @RequiresPermissions("lottery:cart:export")
    @Log(title = "进货管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookCart bookCart)
    {
        List<BookCart> list = bookCartService.selectBookCartList(bookCart);
        ExcelUtil<BookCart> util = new ExcelUtil<BookCart>(BookCart.class);
        return util.exportExcel(list, "进货管理数据");
    }

    /**
     * 新增进货管理
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        model.addAttribute("customers",customerService.selectBookCustomerList(new BookCustomer()));
        model.addAttribute("books",bookBooksService.selectBookBooksList(new BookBooks()));
        return prefix + "/add";
    }

    /**
     * 新增保存进货管理
     */
    @RequiresPermissions("lottery:cart:add")
    @Log(title = "进货管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookCart bookCart)
    {
        return toAjax(bookCartService.insertBookCart(bookCart));
    }
    /**
     * 保存本批进货
     */
    @Autowired
    IBookHistoryService historyService;

    @RequiresPermissions("lottery:cart:add")
    @Log(title = "进货管理", businessType = BusinessType.INSERT)
    @GetMapping("/tohistory")
    @ResponseBody
    public AjaxResult tohistory()
    {
        List<BookCart> carts = bookCartService.selectBookCartList(new BookCart());
        if (carts.size() == 0){
            return error("请先加入采购项");
        }
        List<Long> ids = new ArrayList<>(carts.size());
        carts.forEach(h -> {
            //采购项加入历史记录
            BookHistory history = new BookHistory();
            BeanUtils.copyProperties(h,history);
            history.setCreateTime(new Date());
            historyService.insertBookHistory(history);

            //加库存
            BookBooks book = bookBooksService.selectBookBooksById(h.getBookId());
            book.setAmount(book.getAmount()+h.getAmount());
            bookBooksService.updateBookBooks(book);

            ids.add(h.getId());
        });
        //清空购物车
        bookCartService.deleteBookCartByIds(StringUtils.join(ids,","));
        return success("采购成功");
    }
    /**
     * 修改进货管理
     */
    @RequiresPermissions("lottery:cart:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookCart bookCart = bookCartService.selectBookCartById(id);
        mmap.put("bookCart", bookCart);
        mmap.addAttribute("customers",customerService.selectBookCustomerList(new BookCustomer()));
        mmap.addAttribute("books",bookBooksService.selectBookBooksList(new BookBooks()));
        return prefix + "/edit";
    }

    /**
     * 修改保存进货管理
     */
    @RequiresPermissions("lottery:cart:edit")
    @Log(title = "进货管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookCart bookCart)
    {
        return toAjax(bookCartService.updateBookCart(bookCart));
    }

    /**
     * 删除进货管理
     */
    @RequiresPermissions("lottery:cart:remove")
    @Log(title = "进货管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookCartService.deleteBookCartByIds(ids));
    }
}

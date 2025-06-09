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
@RequestMapping("/lottery/cart")    //  当用户访问 /lottery/orders 这个 URL 时，会触发被该注解修饰的方法进行处理。
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
    //  处理所有发送到”默认路径“的 GET 请求。
    //  @GetMapping("/cart")
    //  映射的具体 URL 路径，默认为空时会继承类级别的 @RequestMapping |
    @GetMapping()
    public String cart(Model model)
    {
        //  将数据添加到模型中，以便在视图（如 Thymeleaf、JSP 页面）中使用
        //  | 参数                                                        |               含义                            |
        //  |"customers"                                                 | 属性名，在视图中可以通过 ${customers} 访问该数据   |
        //  | customerService.selectBookCustomerList(new BookCustomer()) | 获取客户列表的业务逻辑结果，作为属性值              |
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
        //  将业务层返回的结果封装成前端可识别的响应格式，通常是 JSON
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
        // 检查购物车是否为空
        if (carts.size() == 0){
            return error("请先加入采购项");
        }
        // 创建一个列表用于存储购物车中所有采购项的ID
        List<Long> ids = new ArrayList<>(carts.size());
        // 遍历购物车中的每个采购项
        carts.forEach(h -> {
            //采购项加入历史记录
            BookHistory history = new BookHistory();
            // 将BookCart对象的属性复制到BookHistory对象中
            BeanUtils.copyProperties(h,history);
            history.setCreateTime(new Date());
            historyService.insertBookHistory(history);

            //  加库存
            // 根据书籍ID查询书籍信息
            BookBooks book = bookBooksService.selectBookBooksById(h.getBookId());
            // 更新书籍库存数量，增加采购项中的数量
            book.setAmount(book.getAmount()+h.getAmount());
            bookBooksService.updateBookBooks(book);
            // 将采购项的ID添加到列表中
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
        // 将进货信息放入模型中
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
        //  将业务层返回的结果封装成前端可识别的响应格式，通常是 JSON
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
        //  将业务层返回的结果封装成前端可识别的响应格式，通常是 JSON
        return toAjax(bookCartService.deleteBookCartByIds(ids));
    }
}

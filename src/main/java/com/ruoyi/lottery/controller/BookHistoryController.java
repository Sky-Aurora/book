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
import com.ruoyi.lottery.domain.BookHistory;
import com.ruoyi.lottery.service.IBookHistoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 进货记录Controller
 *
 */
@Controller
@RequestMapping("/lottery/history")
public class BookHistoryController extends BaseController
{
    private String prefix = "lottery/history";

    @Autowired
    private IBookHistoryService bookHistoryService;

    @RequiresPermissions("lottery:history:view")
    @GetMapping()
    public String history()
    {
        return prefix + "/history";
    }

    /**
     * 查询进货记录列表
     */
    @RequiresPermissions("lottery:history:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookHistory bookHistory)
    {
        startPage();
        List<BookHistory> list = bookHistoryService.selectBookHistoryList(bookHistory);
        return getDataTable(list);
    }

    /**
     * 导出进货记录列表
     */
    @RequiresPermissions("lottery:history:export")
    @Log(title = "进货记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookHistory bookHistory)
    {
        List<BookHistory> list = bookHistoryService.selectBookHistoryList(bookHistory);
        ExcelUtil<BookHistory> util = new ExcelUtil<BookHistory>(BookHistory.class);
        return util.exportExcel(list, "进货记录数据");
    }

    /**
     * 新增进货记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存进货记录
     */
    @RequiresPermissions("lottery:history:add")
    @Log(title = "进货记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookHistory bookHistory)
    {
        return toAjax(bookHistoryService.insertBookHistory(bookHistory));
    }

    /**
     * 修改进货记录
     */
    @RequiresPermissions("lottery:history:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookHistory bookHistory = bookHistoryService.selectBookHistoryById(id);
        mmap.put("bookHistory", bookHistory);
        return prefix + "/edit";
    }

    /**
     * 修改保存进货记录
     */
    @RequiresPermissions("lottery:history:edit")
    @Log(title = "进货记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookHistory bookHistory)
    {
        return toAjax(bookHistoryService.updateBookHistory(bookHistory));
    }

    /**
     * 删除进货记录
     */
    @RequiresPermissions("lottery:history:remove")
    @Log(title = "进货记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookHistoryService.deleteBookHistoryByIds(ids));
    }
}

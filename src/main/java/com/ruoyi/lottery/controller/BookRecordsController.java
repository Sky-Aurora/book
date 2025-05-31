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
import com.ruoyi.lottery.domain.BookRecords;
import com.ruoyi.lottery.service.IBookRecordsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购记录Controller
 *
 */
@Controller
@RequestMapping("/lottery/records")
public class BookRecordsController extends BaseController
{
    private String prefix = "lottery/records";

    @Autowired
    private IBookRecordsService bookRecordsService;

    @RequiresPermissions("lottery:records:view")
    @GetMapping()
    public String records()
    {
        return prefix + "/records";
    }

    /**
     * 查询采购记录列表
     */
    @RequiresPermissions("lottery:records:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookRecords bookRecords)
    {
        startPage();
        List<BookRecords> list = bookRecordsService.selectBookRecordsList(bookRecords);
        return getDataTable(list);
    }

    /**
     * 导出采购记录列表
     */
    @RequiresPermissions("lottery:records:export")
    @Log(title = "采购记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookRecords bookRecords)
    {
        List<BookRecords> list = bookRecordsService.selectBookRecordsList(bookRecords);
        ExcelUtil<BookRecords> util = new ExcelUtil<BookRecords>(BookRecords.class);
        return util.exportExcel(list, "采购记录数据");
    }

    /**
     * 新增采购记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购记录
     */
    @RequiresPermissions("lottery:records:add")
    @Log(title = "采购记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookRecords bookRecords)
    {
        return toAjax(bookRecordsService.insertBookRecords(bookRecords));
    }

    /**
     * 修改采购记录
     */
    @RequiresPermissions("lottery:records:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookRecords bookRecords = bookRecordsService.selectBookRecordsById(id);
        mmap.put("bookRecords", bookRecords);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购记录
     */
    @RequiresPermissions("lottery:records:edit")
    @Log(title = "采购记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookRecords bookRecords)
    {
        return toAjax(bookRecordsService.updateBookRecords(bookRecords));
    }

    /**
     * 删除采购记录
     */
    @RequiresPermissions("lottery:records:remove")
    @Log(title = "采购记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookRecordsService.deleteBookRecordsByIds(ids));
    }
}

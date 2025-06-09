package com.ruoyi.common.utils;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.sql.SqlUtil;

/**
 * 分页工具类
 * 
 * @author ruoyi
 */
public class PageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        // 构建页面请求对象
        PageDomain pageDomain = TableSupport.buildPageRequest();

        // 获取页码
        Integer pageNum = pageDomain.getPageNum();
        // 获取每页大小
        Integer pageSize = pageDomain.getPageSize();
        // 获取排序条件并进行SQL注入防护处理
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        // 获取是否合理化分页的标志
        Boolean reasonable = pageDomain.getReasonable();

        // 启动分页，设置页码、每页大小、排序条件和是否合理化分页
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }


    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}

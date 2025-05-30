package com.ruoyi.lottery.service;

import java.util.List;
import com.ruoyi.lottery.domain.BookHistory;

/**
 * 进货记录Service接口
 * 
 * @author Shawn
 * @date 2024-03-04
 */
public interface IBookHistoryService 
{
    /**
     * 查询进货记录
     * 
     * @param id 进货记录主键
     * @return 进货记录
     */
    public BookHistory selectBookHistoryById(Long id);

    /**
     * 查询进货记录列表
     * 
     * @param bookHistory 进货记录
     * @return 进货记录集合
     */
    public List<BookHistory> selectBookHistoryList(BookHistory bookHistory);

    /**
     * 新增进货记录
     * 
     * @param bookHistory 进货记录
     * @return 结果
     */
    public int insertBookHistory(BookHistory bookHistory);

    /**
     * 修改进货记录
     * 
     * @param bookHistory 进货记录
     * @return 结果
     */
    public int updateBookHistory(BookHistory bookHistory);

    /**
     * 批量删除进货记录
     * 
     * @param ids 需要删除的进货记录主键集合
     * @return 结果
     */
    public int deleteBookHistoryByIds(String ids);

    /**
     * 删除进货记录信息
     * 
     * @param id 进货记录主键
     * @return 结果
     */
    public int deleteBookHistoryById(Long id);
}

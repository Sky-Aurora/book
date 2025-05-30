package com.ruoyi.lottery.service;

import java.util.List;
import com.ruoyi.lottery.domain.BookRecords;

/**
 * 采购记录Service接口
 * 
 * @author Shawn
 * @date 2024-02-29
 */
public interface IBookRecordsService 
{
    /**
     * 查询采购记录
     * 
     * @param id 采购记录主键
     * @return 采购记录
     */
    public BookRecords selectBookRecordsById(Long id);

    /**
     * 查询采购记录列表
     * 
     * @param bookRecords 采购记录
     * @return 采购记录集合
     */
    public List<BookRecords> selectBookRecordsList(BookRecords bookRecords);

    /**
     * 新增采购记录
     * 
     * @param bookRecords 采购记录
     * @return 结果
     */
    public int insertBookRecords(BookRecords bookRecords);

    /**
     * 修改采购记录
     * 
     * @param bookRecords 采购记录
     * @return 结果
     */
    public int updateBookRecords(BookRecords bookRecords);

    /**
     * 批量删除采购记录
     * 
     * @param ids 需要删除的采购记录主键集合
     * @return 结果
     */
    public int deleteBookRecordsByIds(String ids);

    /**
     * 删除采购记录信息
     * 
     * @param id 采购记录主键
     * @return 结果
     */
    public int deleteBookRecordsById(Long id);
}

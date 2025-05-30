package com.ruoyi.lottery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookRecordsMapper;
import com.ruoyi.lottery.domain.BookRecords;
import com.ruoyi.lottery.service.IBookRecordsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采购记录Service业务层处理
 * 
 * @author Shawn
 * @date 2024-02-29
 */
@Service
public class BookRecordsServiceImpl implements IBookRecordsService 
{
    @Autowired
    private BookRecordsMapper bookRecordsMapper;

    /**
     * 查询采购记录
     * 
     * @param id 采购记录主键
     * @return 采购记录
     */
    @Override
    public BookRecords selectBookRecordsById(Long id)
    {
        return bookRecordsMapper.selectBookRecordsById(id);
    }

    /**
     * 查询采购记录列表
     * 
     * @param bookRecords 采购记录
     * @return 采购记录
     */
    @Override
    public List<BookRecords> selectBookRecordsList(BookRecords bookRecords)
    {
        return bookRecordsMapper.selectBookRecordsList(bookRecords);
    }

    /**
     * 新增采购记录
     * 
     * @param bookRecords 采购记录
     * @return 结果
     */
    @Override
    public int insertBookRecords(BookRecords bookRecords)
    {
        return bookRecordsMapper.insertBookRecords(bookRecords);
    }

    /**
     * 修改采购记录
     * 
     * @param bookRecords 采购记录
     * @return 结果
     */
    @Override
    public int updateBookRecords(BookRecords bookRecords)
    {
        return bookRecordsMapper.updateBookRecords(bookRecords);
    }

    /**
     * 批量删除采购记录
     * 
     * @param ids 需要删除的采购记录主键
     * @return 结果
     */
    @Override
    public int deleteBookRecordsByIds(String ids)
    {
        return bookRecordsMapper.deleteBookRecordsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购记录信息
     * 
     * @param id 采购记录主键
     * @return 结果
     */
    @Override
    public int deleteBookRecordsById(Long id)
    {
        return bookRecordsMapper.deleteBookRecordsById(id);
    }
}

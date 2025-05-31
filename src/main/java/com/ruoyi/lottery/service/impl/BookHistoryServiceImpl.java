package com.ruoyi.lottery.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookHistoryMapper;
import com.ruoyi.lottery.domain.BookHistory;
import com.ruoyi.lottery.service.IBookHistoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 进货记录Service业务层处理
 *
 */
@Service
public class BookHistoryServiceImpl implements IBookHistoryService 
{
    @Autowired
    private BookHistoryMapper bookHistoryMapper;

    /**
     * 查询进货记录
     * 
     * @param id 进货记录主键
     * @return 进货记录
     */
    @Override
    public BookHistory selectBookHistoryById(Long id)
    {
        return bookHistoryMapper.selectBookHistoryById(id);
    }

    /**
     * 查询进货记录列表
     * 
     * @param bookHistory 进货记录
     * @return 进货记录
     */
    @Override
    public List<BookHistory> selectBookHistoryList(BookHistory bookHistory)
    {
        return bookHistoryMapper.selectBookHistoryList(bookHistory);
    }

    /**
     * 新增进货记录
     * 
     * @param bookHistory 进货记录
     * @return 结果
     */
    @Override
    public int insertBookHistory(BookHistory bookHistory)
    {
        bookHistory.setCreateTime(DateUtils.getNowDate());
        return bookHistoryMapper.insertBookHistory(bookHistory);
    }

    /**
     * 修改进货记录
     * 
     * @param bookHistory 进货记录
     * @return 结果
     */
    @Override
    public int updateBookHistory(BookHistory bookHistory)
    {
        return bookHistoryMapper.updateBookHistory(bookHistory);
    }

    /**
     * 批量删除进货记录
     * 
     * @param ids 需要删除的进货记录主键
     * @return 结果
     */
    @Override
    public int deleteBookHistoryByIds(String ids)
    {
        return bookHistoryMapper.deleteBookHistoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除进货记录信息
     * 
     * @param id 进货记录主键
     * @return 结果
     */
    @Override
    public int deleteBookHistoryById(Long id)
    {
        return bookHistoryMapper.deleteBookHistoryById(id);
    }
}

package com.ruoyi.lottery.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookOrderdetailsMapper;
import com.ruoyi.lottery.domain.BookOrderdetails;
import com.ruoyi.lottery.service.IBookOrderdetailsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 订单明细Service业务层处理
 *
 */
@Service
public class BookOrderdetailsServiceImpl implements IBookOrderdetailsService 
{
    @Autowired
    private BookOrderdetailsMapper bookOrderdetailsMapper;

    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    @Override
    public BookOrderdetails selectBookOrderdetailsById(Long id)
    {
        return bookOrderdetailsMapper.selectBookOrderdetailsById(id);
    }

    /**
     * 查询订单明细列表
     * 
     * @param bookOrderdetails 订单明细
     * @return 订单明细
     */
    @Override
    public List<BookOrderdetails> selectBookOrderdetailsList(BookOrderdetails bookOrderdetails)
    {
        return bookOrderdetailsMapper.selectBookOrderdetailsList(bookOrderdetails);
    }

    /**
     * 新增订单明细
     * 
     * @param bookOrderdetails 订单明细
     * @return 结果
     */
    @Override
    public int insertBookOrderdetails(BookOrderdetails bookOrderdetails)
    {
        bookOrderdetails.setCreateTime(DateUtils.getNowDate());
        return bookOrderdetailsMapper.insertBookOrderdetails(bookOrderdetails);
    }

    /**
     * 修改订单明细
     * 
     * @param bookOrderdetails 订单明细
     * @return 结果
     */
    @Override
    public int updateBookOrderdetails(BookOrderdetails bookOrderdetails)
    {
        return bookOrderdetailsMapper.updateBookOrderdetails(bookOrderdetails);
    }

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键
     * @return 结果
     */
    @Override
    public int deleteBookOrderdetailsByIds(String ids)
    {
        return bookOrderdetailsMapper.deleteBookOrderdetailsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    @Override
    public int deleteBookOrderdetailsById(Long id)
    {
        return bookOrderdetailsMapper.deleteBookOrderdetailsById(id);
    }
}

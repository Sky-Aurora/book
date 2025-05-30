package com.ruoyi.lottery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookOrdersMapper;
import com.ruoyi.lottery.domain.BookOrders;
import com.ruoyi.lottery.service.IBookOrdersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 销售订单Service业务层处理
 * 
 * @author Shawn
 * @date 2024-02-29
 */
@Service
public class BookOrdersServiceImpl implements IBookOrdersService 
{
    @Autowired
    private BookOrdersMapper bookOrdersMapper;

    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    @Override
    public BookOrders selectBookOrdersById(Long id)
    {
        return bookOrdersMapper.selectBookOrdersById(id);
    }

    /**
     * 查询销售订单列表
     * 
     * @param bookOrders 销售订单
     * @return 销售订单
     */
    @Override
    public List<BookOrders> selectBookOrdersList(BookOrders bookOrders)
    {
        return bookOrdersMapper.selectBookOrdersList(bookOrders);
    }

    /**
     * 新增销售订单
     * 
     * @param bookOrders 销售订单
     * @return 结果
     */
    @Override
    public int insertBookOrders(BookOrders bookOrders)
    {
        return bookOrdersMapper.insertBookOrders(bookOrders);
    }

    /**
     * 修改销售订单
     * 
     * @param bookOrders 销售订单
     * @return 结果
     */
    @Override
    public int updateBookOrders(BookOrders bookOrders)
    {
        return bookOrdersMapper.updateBookOrders(bookOrders);
    }

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的销售订单主键
     * @return 结果
     */
    @Override
    public int deleteBookOrdersByIds(String ids)
    {
        return bookOrdersMapper.deleteBookOrdersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    @Override
    public int deleteBookOrdersById(Long id)
    {
        return bookOrdersMapper.deleteBookOrdersById(id);
    }
}

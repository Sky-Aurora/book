package com.ruoyi.lottery.service;

import java.util.List;
import com.ruoyi.lottery.domain.BookOrders;

/**
 * 销售订单Service接口
 * 
 * @author Shawn
 * @date 2024-02-29
 */
public interface IBookOrdersService 
{
    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    public BookOrders selectBookOrdersById(Long id);

    /**
     * 查询销售订单列表
     * 
     * @param bookOrders 销售订单
     * @return 销售订单集合
     */
    public List<BookOrders> selectBookOrdersList(BookOrders bookOrders);

    /**
     * 新增销售订单
     * 
     * @param bookOrders 销售订单
     * @return 结果
     */
    public int insertBookOrders(BookOrders bookOrders);

    /**
     * 修改销售订单
     * 
     * @param bookOrders 销售订单
     * @return 结果
     */
    public int updateBookOrders(BookOrders bookOrders);

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的销售订单主键集合
     * @return 结果
     */
    public int deleteBookOrdersByIds(String ids);

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    public int deleteBookOrdersById(Long id);
}

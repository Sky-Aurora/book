package com.ruoyi.lottery.service;

import java.util.List;
import com.ruoyi.lottery.domain.BookOrderdetails;

/**
 * 订单明细Service接口
 *
 */
public interface IBookOrderdetailsService 
{
    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    public BookOrderdetails selectBookOrderdetailsById(Long id);

    /**
     * 查询订单明细列表
     * 
     * @param bookOrderdetails 订单明细
     * @return 订单明细集合
     */
    public List<BookOrderdetails> selectBookOrderdetailsList(BookOrderdetails bookOrderdetails);

    /**
     * 新增订单明细
     * 
     * @param bookOrderdetails 订单明细
     * @return 结果
     */
    public int insertBookOrderdetails(BookOrderdetails bookOrderdetails);

    /**
     * 修改订单明细
     * 
     * @param bookOrderdetails 订单明细
     * @return 结果
     */
    public int updateBookOrderdetails(BookOrderdetails bookOrderdetails);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键集合
     * @return 结果
     */
    public int deleteBookOrderdetailsByIds(String ids);

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteBookOrderdetailsById(Long id);
}

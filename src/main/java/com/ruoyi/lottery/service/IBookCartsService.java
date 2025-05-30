package com.ruoyi.lottery.service;

import java.util.List;
import com.ruoyi.lottery.domain.BookCarts;

/**
 * 购物车Service接口
 * 
 * @author Shawn
 * @date 2024-02-29
 */
public interface IBookCartsService 
{
    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    public BookCarts selectBookCartsById(Long id);

    /**
     * 查询购物车列表
     * 
     * @param bookCarts 购物车
     * @return 购物车集合
     */
    public List<BookCarts> selectBookCartsList(BookCarts bookCarts);

    /**
     * 新增购物车
     * 
     * @param bookCarts 购物车
     * @return 结果
     */
    public int insertBookCarts(BookCarts bookCarts);

    /**
     * 修改购物车
     * 
     * @param bookCarts 购物车
     * @return 结果
     */
    public int updateBookCarts(BookCarts bookCarts);

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的购物车主键集合
     * @return 结果
     */
    public int deleteBookCartsByIds(String ids);

    /**
     * 删除购物车信息
     * 
     * @param id 购物车主键
     * @return 结果
     */
    public int deleteBookCartsById(Long id);
}

package com.ruoyi.lottery.service;

import java.util.List;
import com.ruoyi.lottery.domain.BookCart;

/**
 * 进货管理Service接口
 * 
 * @author Shawn
 * @date 2024-03-04
 */
public interface IBookCartService 
{
    /**
     * 查询进货管理
     * 
     * @param id 进货管理主键
     * @return 进货管理
     */
    public BookCart selectBookCartById(Long id);

    /**
     * 查询进货管理列表
     * 
     * @param bookCart 进货管理
     * @return 进货管理集合
     */
    public List<BookCart> selectBookCartList(BookCart bookCart);

    /**
     * 新增进货管理
     * 
     * @param bookCart 进货管理
     * @return 结果
     */
    public int insertBookCart(BookCart bookCart);

    /**
     * 修改进货管理
     * 
     * @param bookCart 进货管理
     * @return 结果
     */
    public int updateBookCart(BookCart bookCart);

    /**
     * 批量删除进货管理
     * 
     * @param ids 需要删除的进货管理主键集合
     * @return 结果
     */
    public int deleteBookCartByIds(String ids);

    /**
     * 删除进货管理信息
     * 
     * @param id 进货管理主键
     * @return 结果
     */
    public int deleteBookCartById(Long id);
}

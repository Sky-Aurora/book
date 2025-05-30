package com.ruoyi.lottery.mapper;

import java.util.List;
import com.ruoyi.lottery.domain.BookSale;

/**
 * 销售记录Mapper接口
 * 
 * @author Shawn
 * @date 2024-03-05
 */
public interface BookSaleMapper 
{
    /**
     * 查询销售记录
     * 
     * @param id 销售记录主键
     * @return 销售记录
     */
    public BookSale selectBookSaleById(Long id);

    /**
     * 查询销售记录列表
     * 
     * @param bookSale 销售记录
     * @return 销售记录集合
     */
    public List<BookSale> selectBookSaleList(BookSale bookSale);

    /**
     * 新增销售记录
     * 
     * @param bookSale 销售记录
     * @return 结果
     */
    public int insertBookSale(BookSale bookSale);

    /**
     * 修改销售记录
     * 
     * @param bookSale 销售记录
     * @return 结果
     */
    public int updateBookSale(BookSale bookSale);

    /**
     * 删除销售记录
     * 
     * @param id 销售记录主键
     * @return 结果
     */
    public int deleteBookSaleById(Long id);

    /**
     * 批量删除销售记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookSaleByIds(String[] ids);
}

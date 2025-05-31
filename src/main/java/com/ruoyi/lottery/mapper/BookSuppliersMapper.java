package com.ruoyi.lottery.mapper;

import java.util.List;
import com.ruoyi.lottery.domain.BookSuppliers;

/**
 * 采购商Mapper接口
 *
 */
public interface BookSuppliersMapper 
{
    /**
     * 查询采购商
     * 
     * @param id 采购商主键
     * @return 采购商
     */
    public BookSuppliers selectBookSuppliersById(Long id);

    /**
     * 查询采购商列表
     * 
     * @param bookSuppliers 采购商
     * @return 采购商集合
     */
    public List<BookSuppliers> selectBookSuppliersList(BookSuppliers bookSuppliers);

    /**
     * 新增采购商
     * 
     * @param bookSuppliers 采购商
     * @return 结果
     */
    public int insertBookSuppliers(BookSuppliers bookSuppliers);

    /**
     * 修改采购商
     * 
     * @param bookSuppliers 采购商
     * @return 结果
     */
    public int updateBookSuppliers(BookSuppliers bookSuppliers);

    /**
     * 删除采购商
     * 
     * @param id 采购商主键
     * @return 结果
     */
    public int deleteBookSuppliersById(Long id);

    /**
     * 批量删除采购商
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookSuppliersByIds(String[] ids);
}

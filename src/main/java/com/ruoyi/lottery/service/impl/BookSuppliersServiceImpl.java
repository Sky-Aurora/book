package com.ruoyi.lottery.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookSuppliersMapper;
import com.ruoyi.lottery.domain.BookSuppliers;
import com.ruoyi.lottery.service.IBookSuppliersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采购商Service业务层处理
 *
 */
@Service
public class BookSuppliersServiceImpl implements IBookSuppliersService 
{
    @Autowired
    private BookSuppliersMapper bookSuppliersMapper;

    /**
     * 查询采购商
     * 
     * @param id 采购商主键
     * @return 采购商
     */
    @Override
    public BookSuppliers selectBookSuppliersById(Long id)
    {
        return bookSuppliersMapper.selectBookSuppliersById(id);
    }

    /**
     * 查询采购商列表
     * 
     * @param bookSuppliers 采购商
     * @return 采购商
     */
    @Override
    public List<BookSuppliers> selectBookSuppliersList(BookSuppliers bookSuppliers)
    {
        return bookSuppliersMapper.selectBookSuppliersList(bookSuppliers);
    }

    /**
     * 新增采购商
     * 
     * @param bookSuppliers 采购商
     * @return 结果
     */
    @Override
    public int insertBookSuppliers(BookSuppliers bookSuppliers)
    {
        bookSuppliers.setCreateTime(DateUtils.getNowDate());
        return bookSuppliersMapper.insertBookSuppliers(bookSuppliers);
    }

    /**
     * 修改采购商
     * 
     * @param bookSuppliers 采购商
     * @return 结果
     */
    @Override
    public int updateBookSuppliers(BookSuppliers bookSuppliers)
    {
        bookSuppliers.setUpdateTime(DateUtils.getNowDate());
        return bookSuppliersMapper.updateBookSuppliers(bookSuppliers);
    }

    /**
     * 批量删除采购商
     * 
     * @param ids 需要删除的采购商主键
     * @return 结果
     */
    @Override
    public int deleteBookSuppliersByIds(String ids)
    {
        return bookSuppliersMapper.deleteBookSuppliersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购商信息
     * 
     * @param id 采购商主键
     * @return 结果
     */
    @Override
    public int deleteBookSuppliersById(Long id)
    {
        return bookSuppliersMapper.deleteBookSuppliersById(id);
    }
}

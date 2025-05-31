package com.ruoyi.lottery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookCustomersMapper;
import com.ruoyi.lottery.domain.BookCustomers;
import com.ruoyi.lottery.service.IBookCustomersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户Service业务层处理
 *
 */
@Service
public class BookCustomersServiceImpl implements IBookCustomersService 
{
    @Autowired
    private BookCustomersMapper bookCustomersMapper;

    /**
     * 查询客户
     * 
     * @param id 客户主键
     * @return 客户
     */
    @Override
    public BookCustomers selectBookCustomersById(Long id)
    {
        return bookCustomersMapper.selectBookCustomersById(id);
    }

    /**
     * 查询客户列表
     * 
     * @param bookCustomers 客户
     * @return 客户
     */
    @Override
    public List<BookCustomers> selectBookCustomersList(BookCustomers bookCustomers)
    {
        return bookCustomersMapper.selectBookCustomersList(bookCustomers);
    }

    /**
     * 新增客户
     * 
     * @param bookCustomers 客户
     * @return 结果
     */
    @Override
    public int insertBookCustomers(BookCustomers bookCustomers)
    {
        return bookCustomersMapper.insertBookCustomers(bookCustomers);
    }

    /**
     * 修改客户
     * 
     * @param bookCustomers 客户
     * @return 结果
     */
    @Override
    public int updateBookCustomers(BookCustomers bookCustomers)
    {
        return bookCustomersMapper.updateBookCustomers(bookCustomers);
    }

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteBookCustomersByIds(String ids)
    {
        return bookCustomersMapper.deleteBookCustomersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息
     * 
     * @param id 客户主键
     * @return 结果
     */
    @Override
    public int deleteBookCustomersById(Long id)
    {
        return bookCustomersMapper.deleteBookCustomersById(id);
    }
}

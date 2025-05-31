package com.ruoyi.lottery.mapper;

import java.util.List;
import com.ruoyi.lottery.domain.BookCustomers;

/**
 * 客户Mapper接口
 *
 */
public interface BookCustomersMapper 
{
    /**
     * 查询客户
     * 
     * @param id 客户主键
     * @return 客户
     */
    public BookCustomers selectBookCustomersById(Long id);

    /**
     * 查询客户列表
     * 
     * @param bookCustomers 客户
     * @return 客户集合
     */
    public List<BookCustomers> selectBookCustomersList(BookCustomers bookCustomers);

    /**
     * 新增客户
     * 
     * @param bookCustomers 客户
     * @return 结果
     */
    public int insertBookCustomers(BookCustomers bookCustomers);

    /**
     * 修改客户
     * 
     * @param bookCustomers 客户
     * @return 结果
     */
    public int updateBookCustomers(BookCustomers bookCustomers);

    /**
     * 删除客户
     * 
     * @param id 客户主键
     * @return 结果
     */
    public int deleteBookCustomersById(Long id);

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookCustomersByIds(String[] ids);
}

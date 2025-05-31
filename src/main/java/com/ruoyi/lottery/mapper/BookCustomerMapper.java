package com.ruoyi.lottery.mapper;

import java.util.List;
import com.ruoyi.lottery.domain.BookCustomer;

/**
 * 客户管理Mapper接口
 *
 */
public interface BookCustomerMapper 
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    public BookCustomer selectBookCustomerById(Long id);

    /**
     * 查询客户管理列表
     * 
     * @param bookCustomer 客户管理
     * @return 客户管理集合
     */
    public List<BookCustomer> selectBookCustomerList(BookCustomer bookCustomer);

    /**
     * 新增客户管理
     * 
     * @param bookCustomer 客户管理
     * @return 结果
     */
    public int insertBookCustomer(BookCustomer bookCustomer);

    /**
     * 修改客户管理
     * 
     * @param bookCustomer 客户管理
     * @return 结果
     */
    public int updateBookCustomer(BookCustomer bookCustomer);

    /**
     * 删除客户管理
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteBookCustomerById(Long id);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookCustomerByIds(String[] ids);
}

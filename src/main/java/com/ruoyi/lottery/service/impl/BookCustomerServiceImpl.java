package com.ruoyi.lottery.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.lottery.domain.BookBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookCustomerMapper;
import com.ruoyi.lottery.domain.BookCustomer;
import com.ruoyi.lottery.service.IBookCustomerService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户管理Service业务层处理
 * 
 * @author Shawn
 * @date 2024-03-04
 */
@Service("customers")
public class BookCustomerServiceImpl implements IBookCustomerService 
{
    @Autowired
    private BookCustomerMapper bookCustomerMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public BookCustomer selectBookCustomerById(Long id)
    {
        return bookCustomerMapper.selectBookCustomerById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param bookCustomer 客户管理
     * @return 客户管理
     */
    @Override
    public List<BookCustomer> selectBookCustomerList(BookCustomer bookCustomer)
    {
        return bookCustomerMapper.selectBookCustomerList(bookCustomer);
    }
    public List<BookCustomer> getAll()
    {
        return bookCustomerMapper.selectBookCustomerList(new BookCustomer());
    }
    public List<SysDictData> toDict(){
        List<BookCustomer> list = getAll();
        List<SysDictData> list1 = new ArrayList<>();
        list.forEach(e -> {
            SysDictData dictData = new SysDictData();
            dictData.setDictLabel(e.getName());
            dictData.setDictValue(e.getId()+"");
            list1.add(dictData);
        });
        return list1;
    }
    /**
     * 新增客户管理
     * 
     * @param bookCustomer 客户管理
     * @return 结果
     */
    @Override
    public int insertBookCustomer(BookCustomer bookCustomer)
    {
        return bookCustomerMapper.insertBookCustomer(bookCustomer);
    }

    /**
     * 修改客户管理
     * 
     * @param bookCustomer 客户管理
     * @return 结果
     */
    @Override
    public int updateBookCustomer(BookCustomer bookCustomer)
    {
        return bookCustomerMapper.updateBookCustomer(bookCustomer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteBookCustomerByIds(String ids)
    {
        return bookCustomerMapper.deleteBookCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteBookCustomerById(Long id)
    {
        return bookCustomerMapper.deleteBookCustomerById(id);
    }
}

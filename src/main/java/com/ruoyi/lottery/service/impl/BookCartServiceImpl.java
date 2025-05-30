package com.ruoyi.lottery.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookCartMapper;
import com.ruoyi.lottery.domain.BookCart;
import com.ruoyi.lottery.service.IBookCartService;
import com.ruoyi.common.core.text.Convert;

/**
 * 进货管理Service业务层处理
 * 
 * @author Shawn
 * @date 2024-03-04
 */
@Service
public class BookCartServiceImpl implements IBookCartService 
{
    @Autowired
    private BookCartMapper bookCartMapper;

    /**
     * 查询进货管理
     * 
     * @param id 进货管理主键
     * @return 进货管理
     */
    @Override
    public BookCart selectBookCartById(Long id)
    {
        return bookCartMapper.selectBookCartById(id);
    }

    /**
     * 查询进货管理列表
     * 
     * @param bookCart 进货管理
     * @return 进货管理
     */
    @Override
    public List<BookCart> selectBookCartList(BookCart bookCart)
    {
        return bookCartMapper.selectBookCartList(bookCart);
    }

    /**
     * 新增进货管理
     * 
     * @param bookCart 进货管理
     * @return 结果
     */
    @Override
    public int insertBookCart(BookCart bookCart)
    {
        bookCart.setCreateTime(DateUtils.getNowDate());
        return bookCartMapper.insertBookCart(bookCart);
    }

    /**
     * 修改进货管理
     * 
     * @param bookCart 进货管理
     * @return 结果
     */
    @Override
    public int updateBookCart(BookCart bookCart)
    {
        return bookCartMapper.updateBookCart(bookCart);
    }

    /**
     * 批量删除进货管理
     * 
     * @param ids 需要删除的进货管理主键
     * @return 结果
     */
    @Override
    public int deleteBookCartByIds(String ids)
    {
        return bookCartMapper.deleteBookCartByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除进货管理信息
     * 
     * @param id 进货管理主键
     * @return 结果
     */
    @Override
    public int deleteBookCartById(Long id)
    {
        return bookCartMapper.deleteBookCartById(id);
    }
}

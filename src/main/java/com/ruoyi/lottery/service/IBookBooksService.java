package com.ruoyi.lottery.service;

import java.util.List;
import com.ruoyi.lottery.domain.BookBooks;

/**
 * 图书管理Service接口
 *
 */
public interface IBookBooksService 
{
    /**
     * 查询图书管理
     * 
     * @param id 图书管理主键
     * @return 图书管理
     */
    public BookBooks selectBookBooksById(Long id);

    /**
     * 查询图书管理列表
     * 
     * @param bookBooks 图书管理
     * @return 图书管理集合
     */
    public List<BookBooks> selectBookBooksList(BookBooks bookBooks);

    /**
     * 新增图书管理
     * 
     * @param bookBooks 图书管理
     * @return 结果
     */
    public int insertBookBooks(BookBooks bookBooks);

    /**
     * 修改图书管理
     * 
     * @param bookBooks 图书管理
     * @return 结果
     */
    public int updateBookBooks(BookBooks bookBooks);

    /**
     * 批量删除图书管理
     * 
     * @param ids 需要删除的图书管理主键集合
     * @return 结果
     */
    public int deleteBookBooksByIds(String ids);

    /**
     * 删除图书管理信息
     * 
     * @param id 图书管理主键
     * @return 结果
     */
    public int deleteBookBooksById(Long id);
}

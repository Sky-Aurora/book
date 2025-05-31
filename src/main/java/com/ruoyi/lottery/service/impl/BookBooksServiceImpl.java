package com.ruoyi.lottery.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lottery.mapper.BookBooksMapper;
import com.ruoyi.lottery.domain.BookBooks;
import com.ruoyi.lottery.service.IBookBooksService;
import com.ruoyi.common.core.text.Convert;

/**
 * 图书管理Service业务层处理
 *
 */
@Service("books")
public class BookBooksServiceImpl implements IBookBooksService 
{
    @Autowired
    private BookBooksMapper bookBooksMapper;

    /**
     * 查询图书管理
     * 
     * @param id 图书管理主键
     * @return 图书管理
     */
    @Override
    public BookBooks selectBookBooksById(Long id)
    {
        return bookBooksMapper.selectBookBooksById(id);
    }

    /**
     * 查询图书管理列表
     * 
     * @param bookBooks 图书管理
     * @return 图书管理
     */
    @Override
    public List<BookBooks> selectBookBooksList(BookBooks bookBooks)
    {
        return bookBooksMapper.selectBookBooksList(bookBooks);
    }

    public List<BookBooks> getAll()
    {
        return bookBooksMapper.selectBookBooksList(new BookBooks());
    }

    public List<SysDictData> toDict(){
        List<BookBooks> list = getAll();
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
     * 新增图书管理
     * 
     * @param bookBooks 图书管理
     * @return 结果
     */
    @Override
    public int insertBookBooks(BookBooks bookBooks)
    {
        return bookBooksMapper.insertBookBooks(bookBooks);
    }

    /**
     * 修改图书管理
     * 
     * @param bookBooks 图书管理
     * @return 结果
     */
    @Override
    public int updateBookBooks(BookBooks bookBooks)
    {
        return bookBooksMapper.updateBookBooks(bookBooks);
    }

    /**
     * 批量删除图书管理
     * 
     * @param ids 需要删除的图书管理主键
     * @return 结果
     */
    @Override
    public int deleteBookBooksByIds(String ids)
    {
        return bookBooksMapper.deleteBookBooksByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除图书管理信息
     * 
     * @param id 图书管理主键
     * @return 结果
     */
    @Override
    public int deleteBookBooksById(Long id)
    {
        return bookBooksMapper.deleteBookBooksById(id);
    }
}

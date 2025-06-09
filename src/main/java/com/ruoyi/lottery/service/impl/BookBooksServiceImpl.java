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

    /**
     * 将图书信息转换为字典数据格式，用于前端展示
     *
     * 此方法的作用是将图书对象列表转换为字典数据列表，以便在前端界面上更容易地展示和使用这些数据
     * 它主要负责映射图书的名称和ID到新的SysDictData对象中，作为字典的标签和值
     *
     * @return 返回一个SysDictData对象列表，每个对象包含了图书的名称和ID，格式化为字典数据
     */
    public List<SysDictData> toDict(){
        // 获取所有图书信息
        List<BookBooks> list = getAll();
        // 初始化一个SysDictData列表，用于存储转换后的字典数据
        List<SysDictData> list1 = new ArrayList<>();
        // 遍历图书列表，将每本图书的信息转换为字典数据格式
        list.forEach(e -> {
            // 创建一个新的SysDictData对象，用于表示字典中的一个条目
            SysDictData dictData = new SysDictData();
            // 设置字典条目的标签为图书的名称
            dictData.setDictLabel(e.getName());
            // 设置字典条目的值为图书的ID，转换为字符串格式
            dictData.setDictValue(e.getId()+"");
            // 将转换后的字典条目添加到列表中
            list1.add(dictData);
        });
        // 返回转换后的字典数据列表
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
        //  为了适配后端批量删除操作的数据处理需求
        //   Convert.toStrArray(ids) 将字符串参数 ids 转换为字符串数组（String[]）
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

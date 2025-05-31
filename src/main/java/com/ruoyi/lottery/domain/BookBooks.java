package com.ruoyi.lottery.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书管理对象 book_books
 *
 */
public class BookBooks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 书名 */
    @Excel(name = "书名")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String info;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 出版社 */
    @Excel(name = "出版社")
    private String publisher;

    /** 分类 */
    @Excel(name = "分类")
    private String category;

    /** 图片 */
    @Excel(name = "图片")
    private String pic;

    /** 出版时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出版时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date pubTime;

    /** 库存 */
    @Excel(name = "库存")
    private Long amount;

    /** 销售价 */
    @Excel(name = "销售价")
    private Long price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setInfo(String info) 
    {
        this.info = info;
    }

    public String getInfo() 
    {
        return info;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public String getPublisher() 
    {
        return publisher;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setPic(String pic) 
    {
        this.pic = pic;
    }

    public String getPic() 
    {
        return pic;
    }
    public void setPubTime(Date pubTime) 
    {
        this.pubTime = pubTime;
    }

    public Date getPubTime() 
    {
        return pubTime;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("info", getInfo())
            .append("author", getAuthor())
            .append("publisher", getPublisher())
            .append("category", getCategory())
            .append("pic", getPic())
            .append("pubTime", getPubTime())
            .append("amount", getAmount())
            .append("price", getPrice())
            .toString();
    }
}

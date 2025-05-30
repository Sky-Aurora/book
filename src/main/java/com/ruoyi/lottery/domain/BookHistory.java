package com.ruoyi.lottery.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 进货记录对象 book_history
 * 
 * @author Shawn
 * @date 2024-03-04
 */
public class BookHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 采购图书 */
    @Excel(name = "采购图书")
    private Long bookId;

    /** 采购数量 */
    @Excel(name = "采购数量")
    private Integer amount;

    /** 采购客户 */
    @Excel(name = "采购客户")
    private Long customerId;

    /** 采购价 */
    @Excel(name = "采购价")
    private Long price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setAmount(Integer amount) 
    {
        this.amount = amount;
    }

    public Integer getAmount() 
    {
        return amount;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
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
            .append("bookId", getBookId())
            .append("amount", getAmount())
            .append("customerId", getCustomerId())
            .append("createTime", getCreateTime())
            .append("price", getPrice())
            .toString();
    }
}

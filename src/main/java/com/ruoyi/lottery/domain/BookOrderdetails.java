package com.ruoyi.lottery.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单明细对象 book_orderdetails
 * 
 * @author Shawn
 * @date 2024-02-29
 */
public class BookOrderdetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 销售订单表的订单ID */
    @Excel(name = "销售订单表的订单ID")
    private Long orderId;

    /** 书本id */
    @Excel(name = "书本id")
    private Long bookId;

    /** 购买的数量 */
    @Excel(name = "购买的数量")
    private Long quantity;

    /** 总价 */
    @Excel(name = "总价")
    private Long totalamount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setTotalamount(Long totalamount) 
    {
        this.totalamount = totalamount;
    }

    public Long getTotalamount() 
    {
        return totalamount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("bookId", getBookId())
            .append("quantity", getQuantity())
            .append("totalamount", getTotalamount())
            .append("createTime", getCreateTime())
            .toString();
    }
}

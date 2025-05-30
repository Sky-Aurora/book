package com.ruoyi.lottery.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售订单对象 book_orders
 * 
 * @author Shawn
 * @date 2024-02-29
 */
public class BookOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long id;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 订单总金额 */
    @Excel(name = "订单总金额")
    private Long totalamount;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long status;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    /** 送货地址 */
    @Excel(name = "送货地址")
    private String deliveryAddre;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setTotalamount(Long totalamount) 
    {
        this.totalamount = totalamount;
    }

    public Long getTotalamount() 
    {
        return totalamount;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }
    public void setDeliveryAddre(String deliveryAddre) 
    {
        this.deliveryAddre = deliveryAddre;
    }

    public String getDeliveryAddre() 
    {
        return deliveryAddre;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("totalamount", getTotalamount())
            .append("status", getStatus())
            .append("orderDate", getOrderDate())
            .append("deliveryAddre", getDeliveryAddre())
            .toString();
    }
}

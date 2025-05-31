package com.ruoyi.lottery.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购商对象 book_suppliers
 *
 */
public class BookSuppliers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购商id */
    private Long id;

    /** 采购商名称 */
    @Excel(name = "采购商名称")
    private String supplier;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactName;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** 电子邮件 */
    @Excel(name = "电子邮件")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 注册号 */
    @Excel(name = "注册号")
    private String registrationNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setRegistrationNumber(String registrationNumber) 
    {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() 
    {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("supplier", getSupplier())
            .append("contactName", getContactName())
            .append("phoneNumber", getPhoneNumber())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("registrationNumber", getRegistrationNumber())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

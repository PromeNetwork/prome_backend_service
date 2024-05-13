package com.ruoyi.biz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * coupon对象 coupon
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public class Coupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 代码 */
    @Excel(name = "代码")
    private String code;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 序号 */
    @Excel(name = "序号")
    private Long serial;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setSerial(Long serial) 
    {
        this.serial = serial;
    }

    public Long getSerial() 
    {
        return serial;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("address", getAddress())
            .append("code", getCode())
            .append("status", getStatus())
            .append("serial", getSerial())
            .toString();
    }
}

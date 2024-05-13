package com.ruoyi.biz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * e4c对象 e4c_record
 * 
 * @author kevin
 * @date 2024-03-27
 */
public class E4cRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 数量 */
    @Excel(name = "数量")
    private String e4c;

    /** 接收人 */
    @Excel(name = "接收人")
    private String recipient;

    /** 时间 */
    @Excel(name = "时间")
    private Long timestamp;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 业务唯一id */
    @Excel(name = "业务唯一id")
    private String txId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setE4c(String e4c) 
    {
        this.e4c = e4c;
    }

    public String getE4c() 
    {
        return e4c;
    }
    public void setRecipient(String recipient) 
    {
        this.recipient = recipient;
    }

    public String getRecipient() 
    {
        return recipient;
    }
    public void setTimestamp(Long timestamp) 
    {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() 
    {
        return timestamp;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setTxId(String txId) 
    {
        this.txId = txId;
    }

    public String getTxId() 
    {
        return txId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("e4c", getE4c())
            .append("recipient", getRecipient())
            .append("timestamp", getTimestamp())
            .append("description", getDescription())
            .append("tag", getTag())
            .append("txId", getTxId())
            .toString();
    }
}

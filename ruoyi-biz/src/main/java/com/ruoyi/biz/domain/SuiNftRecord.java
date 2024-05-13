package com.ruoyi.biz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * nft对象 sui_nft_record
 * 
 * @author keivn
 * @date 2024-03-27
 */
public class SuiNftRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** sender */
    @Excel(name = "sender")
    private String sender;

    /** receiver */
    @Excel(name = "receiver")
    private String receiver;

    /** amount */
    @Excel(name = "amount")
    private String amount;

    /** txtype */
    @Excel(name = "txtype")
    private String txtype;

    /** digest */
    @Excel(name = "digest")
    private String digest;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String checkpoint;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cointype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String objectid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long timestamp;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSender(String sender) 
    {
        this.sender = sender;
    }

    public String getSender() 
    {
        return sender;
    }
    public void setReceiver(String receiver) 
    {
        this.receiver = receiver;
    }

    public String getReceiver() 
    {
        return receiver;
    }
    public void setAmount(String amount) 
    {
        this.amount = amount;
    }

    public String getAmount() 
    {
        return amount;
    }
    public void setTxtype(String txtype) 
    {
        this.txtype = txtype;
    }

    public String getTxtype() 
    {
        return txtype;
    }
    public void setDigest(String digest) 
    {
        this.digest = digest;
    }

    public String getDigest() 
    {
        return digest;
    }
    public void setCheckpoint(String checkpoint) 
    {
        this.checkpoint = checkpoint;
    }

    public String getCheckpoint() 
    {
        return checkpoint;
    }
    public void setCointype(String cointype) 
    {
        this.cointype = cointype;
    }

    public String getCointype() 
    {
        return cointype;
    }
    public void setObjectid(String objectid) 
    {
        this.objectid = objectid;
    }

    public String getObjectid() 
    {
        return objectid;
    }
    public void setTimestamp(Long timestamp) 
    {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() 
    {
        return timestamp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sender", getSender())
            .append("receiver", getReceiver())
            .append("amount", getAmount())
            .append("txtype", getTxtype())
            .append("digest", getDigest())
            .append("checkpoint", getCheckpoint())
            .append("cointype", getCointype())
            .append("objectid", getObjectid())
            .append("timestamp", getTimestamp())
            .toString();
    }
}

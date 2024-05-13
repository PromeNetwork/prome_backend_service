package com.ruoyi.biz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * account对象 account_v2
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public class AccountV2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 唯一id */
    @Excel(name = "唯一id")
    private String uid;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String username;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 是否验证 */
    @Excel(name = "是否验证")
    private String isEmailVerified;

    /** 注册时间 */
    @Excel(name = "注册时间")
    private Long registerTimestamp;

    /** 分享码 */
    @Excel(name = "分享码")
    private String randomString;

    /** 密码 */
    @Excel(name = "密码")
    private String passwordHash;

    /** 新建 */
    @Excel(name = "新建")
    private String newsLetterSubscription;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 第三方 */
    @Excel(name = "第三方")
    private String thirdPartyLoginField;

    /** 注册类型 */
    @Excel(name = "注册类型")
    private String registerType;

    /** 推荐人 */
    @Excel(name = "推荐人")
    private String referrer;

    /** 钱包 */
    private String wallets;

    /** 社交账号 */
    @Excel(name = "社交账号")
    private String socialAccountGoogle;

    /** 是否旧账户 */
    @Excel(name = "是否旧账户")
    private String isOldAccount;

    /** 账户标签 */
    @Excel(name = "账户标签")
    private String accountTag;

    /** 来源 */
    @Excel(name = "来源")
    private String gamePackageId;

    /** sui钱包 */
    @Excel(name = "sui钱包")
    private String blockusSuiWallet;

    /** 父地址 */
    @Excel(name = "父地址")
    private String parent;

    /** 推荐路径 */
    @Excel(name = "推荐路径")
    private String paths;

    /** 钱包地址 */
    @Excel(name = "钱包地址")
    private String address;

    @Excel(name = "自己的任务完成数")
    private int selfCount;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Excel(name = "查询码")
    private String inviteCode;

    public int getSelfCount() {
        return selfCount;
    }

    public void setSelfCount(int selfCount) {
        this.selfCount = selfCount;
    }

    public int getRefererCount() {
        return refererCount;
    }

    public void setRefererCount(int refererCount) {
        this.refererCount = refererCount;
    }

    @Excel(name = "推荐的人数")
    private  int refererCount;
    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    @Excel(name = "任务完成的推荐人数")
    private int taskCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setIsEmailVerified(String isEmailVerified) 
    {
        this.isEmailVerified = isEmailVerified;
    }

    public String getIsEmailVerified() 
    {
        return isEmailVerified;
    }
    public void setRegisterTimestamp(Long registerTimestamp) 
    {
        this.registerTimestamp = registerTimestamp;
    }

    public Long getRegisterTimestamp() 
    {
        return registerTimestamp;
    }
    public void setRandomString(String randomString) 
    {
        this.randomString = randomString;
    }

    public String getRandomString() 
    {
        return randomString;
    }
    public void setPasswordHash(String passwordHash) 
    {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() 
    {
        return passwordHash;
    }
    public void setNewsLetterSubscription(String newsLetterSubscription) 
    {
        this.newsLetterSubscription = newsLetterSubscription;
    }

    public String getNewsLetterSubscription() 
    {
        return newsLetterSubscription;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setThirdPartyLoginField(String thirdPartyLoginField) 
    {
        this.thirdPartyLoginField = thirdPartyLoginField;
    }

    public String getThirdPartyLoginField() 
    {
        return thirdPartyLoginField;
    }
    public void setRegisterType(String registerType) 
    {
        this.registerType = registerType;
    }

    public String getRegisterType() 
    {
        return registerType;
    }
    public void setReferrer(String referrer) 
    {
        this.referrer = referrer;
    }

    public String getReferrer() 
    {
        return referrer;
    }
    public void setWallets(String wallets) 
    {
        this.wallets = wallets;
    }

    public String getWallets() 
    {
        return wallets;
    }
    public void setSocialAccountGoogle(String socialAccountGoogle) 
    {
        this.socialAccountGoogle = socialAccountGoogle;
    }

    public String getSocialAccountGoogle() 
    {
        return socialAccountGoogle;
    }
    public void setIsOldAccount(String isOldAccount) 
    {
        this.isOldAccount = isOldAccount;
    }

    public String getIsOldAccount() 
    {
        return isOldAccount;
    }
    public void setAccountTag(String accountTag) 
    {
        this.accountTag = accountTag;
    }

    public String getAccountTag() 
    {
        return accountTag;
    }
    public void setGamePackageId(String gamePackageId) 
    {
        this.gamePackageId = gamePackageId;
    }

    public String getGamePackageId() 
    {
        return gamePackageId;
    }
    public void setBlockusSuiWallet(String blockusSuiWallet) 
    {
        this.blockusSuiWallet = blockusSuiWallet;
    }

    public String getBlockusSuiWallet() 
    {
        return blockusSuiWallet;
    }
    public void setParent(String parent) 
    {
        this.parent = parent;
    }

    public String getParent() 
    {
        return parent;
    }
    public void setPaths(String paths) 
    {
        this.paths = paths;
    }

    public String getPaths() 
    {
        return paths;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("username", getUsername())
            .append("email", getEmail())
            .append("isEmailVerified", getIsEmailVerified())
            .append("registerTimestamp", getRegisterTimestamp())
            .append("randomString", getRandomString())
            .append("passwordHash", getPasswordHash())
            .append("newsLetterSubscription", getNewsLetterSubscription())
            .append("avatar", getAvatar())
            .append("thirdPartyLoginField", getThirdPartyLoginField())
            .append("registerType", getRegisterType())
            .append("referrer", getReferrer())
            .append("wallets", getWallets())
            .append("socialAccountGoogle", getSocialAccountGoogle())
            .append("isOldAccount", getIsOldAccount())
            .append("accountTag", getAccountTag())
            .append("gamePackageId", getGamePackageId())
            .append("blockusSuiWallet", getBlockusSuiWallet())
            .append("parent", getParent())
            .append("paths", getPaths())
            .append("address", getAddress())
            .toString();
    }
}

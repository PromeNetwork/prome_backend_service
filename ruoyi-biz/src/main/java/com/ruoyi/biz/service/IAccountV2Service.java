package com.ruoyi.biz.service;

import java.util.List;
import com.ruoyi.biz.domain.AccountV2;

/**
 * 账户Service接口
 * 
 * @author keivn
 * @date 2024-03-26
 */
public interface IAccountV2Service 
{
    /**
     * 查询账户
     * 
     * @param id 账户主键
     * @return 账户
     */
    public AccountV2 selectAccountV2ById(Long id);

    /**
     * 查询账户列表
     * 
     * @param accountV2 账户
     * @return 账户集合
     */
    public List<AccountV2> selectAccountV2List(AccountV2 accountV2);

    /**
     * 新增账户
     * 
     * @param accountV2 账户
     * @return 结果
     */
    public int insertAccountV2(AccountV2 accountV2);

    /**
     * 修改账户
     * 
     * @param accountV2 账户
     * @return 结果
     */
    public int updateAccountV2(AccountV2 accountV2);

    /**
     * 批量删除账户
     * 
     * @param ids 需要删除的账户主键集合
     * @return 结果
     */
    public int deleteAccountV2ByIds(Long[] ids);

    /**
     * 删除账户信息
     * 
     * @param id 账户主键
     * @return 结果
     */
    public int deleteAccountV2ById(Long id);
}

package com.ruoyi.biz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.biz.mapper.AccountV2Mapper;
import com.ruoyi.biz.domain.AccountV2;
import com.ruoyi.biz.mapper.TaskMapper;
import com.ruoyi.biz.service.IAccountV2Service;
import com.ruoyi.common.utils.StringUtils;

/**
 * 账户Service业务层处理
 * 
 * @author keivn
 * @date 2024-03-26
 */
@Service
public class AccountV2ServiceImpl implements IAccountV2Service 
{
    @Autowired
    private AccountV2Mapper accountV2Mapper;

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询账户
     * 
     * @param id 账户主键
     * @return 账户
     */
    @Override
    public AccountV2 selectAccountV2ById(Long id)
    {
        return accountV2Mapper.selectAccountV2ById(id);
    }

    /**
     * 查询账户列表
     * 
     * @param accountV2 账户
     * @return 账户
     */
    @Override
    public List<AccountV2> selectAccountV2List(AccountV2 accountV2)
    {
        List<AccountV2> accountList = accountV2Mapper.selectAccountV2List(accountV2);
        accountList.forEach(account -> {
            account.setSelfCount(taskMapper.selectTaskCount(account.getAddress()));
            if(StringUtils.isEmpty(account.getRandomString())){
                account.setRefererCount(0);
                return;
            }
            account.setRefererCount(accountV2Mapper.selectAccountV2Count(account.getRandomString()));
        });
        return accountList;
    }

    /**
     * 新增账户
     * 
     * @param accountV2 账户
     * @return 结果
     */
    @Override
    public int insertAccountV2(AccountV2 accountV2)
    {
        return accountV2Mapper.insertAccountV2(accountV2);
    }

    /**
     * 修改账户
     * 
     * @param accountV2 账户
     * @return 结果
     */
    @Override
    public int updateAccountV2(AccountV2 accountV2)
    {
        return accountV2Mapper.updateAccountV2(accountV2);
    }

    /**
     * 批量删除账户
     * 
     * @param ids 需要删除的账户主键
     * @return 结果
     */
    @Override
    public int deleteAccountV2ByIds(Long[] ids)
    {
        return accountV2Mapper.deleteAccountV2ByIds(ids);
    }

    /**
     * 删除账户信息
     * 
     * @param id 账户主键
     * @return 结果
     */
    @Override
    public int deleteAccountV2ById(Long id)
    {
        return accountV2Mapper.deleteAccountV2ById(id);
    }
}

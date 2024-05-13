package com.ruoyi.biz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.biz.mapper.SuiNftRecordMapper;
import com.ruoyi.biz.domain.SuiNftRecord;
import com.ruoyi.biz.service.ISuiNftRecordService;
import com.ruoyi.biz.vi.SuiDataStatics;

/**
 * nftService业务层处理
 * 
 * @author keivn
 * @date 2024-03-27
 */
@Service
public class SuiNftRecordServiceImpl implements ISuiNftRecordService 
{
    @Autowired
    private SuiNftRecordMapper suiNftRecordMapper;

    /**
     * 查询nft
     * 
     * @param id nft主键
     * @return nft
     */
    @Override
    public SuiNftRecord selectSuiNftRecordById(Long id)
    {
        return suiNftRecordMapper.selectSuiNftRecordById(id);
    }

    /**
     * 查询nft列表
     * 
     * @param suiNftRecord nft
     * @return nft
     */
    @Override
    public List<SuiNftRecord> selectSuiNftRecordList(SuiNftRecord suiNftRecord)
    {
        return suiNftRecordMapper.selectSuiNftRecordList(suiNftRecord);
    }

    /**
     * 新增nft
     * 
     * @param suiNftRecord nft
     * @return 结果
     */
    @Override
    public int insertSuiNftRecord(SuiNftRecord suiNftRecord)
    {
        return suiNftRecordMapper.insertSuiNftRecord(suiNftRecord);
    }

    /**
     * 修改nft
     * 
     * @param suiNftRecord nft
     * @return 结果
     */
    @Override
    public int updateSuiNftRecord(SuiNftRecord suiNftRecord)
    {
        return suiNftRecordMapper.updateSuiNftRecord(suiNftRecord);
    }

    /**
     * 批量删除nft
     * 
     * @param ids 需要删除的nft主键
     * @return 结果
     */
    @Override
    public int deleteSuiNftRecordByIds(Long[] ids)
    {
        return suiNftRecordMapper.deleteSuiNftRecordByIds(ids);
    }

    /**
     * 删除nft信息
     * 
     * @param id nft主键
     * @return 结果
     */
    @Override
    public int deleteSuiNftRecordById(Long id)
    {
        return suiNftRecordMapper.deleteSuiNftRecordById(id);
    }

    @Override
    public SuiDataStatics selectSuiDataStatics() {
        SuiDataStatics suiDataStatics = new SuiDataStatics();
        suiDataStatics.setShredTxByDay( suiNftRecordMapper.selectShredByDay());
        suiDataStatics.setRank(suiNftRecordMapper.selectRank());
        suiDataStatics.setShredMintByDay(suiNftRecordMapper.selectShredMintByDay());
        suiDataStatics.setSwordTxByDay(suiNftRecordMapper.selectSwordTxByDay());
        suiDataStatics.setSwordMintByDay(suiNftRecordMapper.selectSwordMintByDay());
        suiDataStatics.setTotalStatu(suiNftRecordMapper.selectTotalStatus());
        return suiDataStatics;
    }
}

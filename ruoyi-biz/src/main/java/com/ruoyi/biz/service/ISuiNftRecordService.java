package com.ruoyi.biz.service;

import java.util.List;
import com.ruoyi.biz.domain.SuiNftRecord;
import com.ruoyi.biz.vi.SuiDataStatics;

/**
 * nftService接口
 * 
 * @author keivn
 * @date 2024-03-27
 */
public interface ISuiNftRecordService 
{
    /**
     * 查询nft
     * 
     * @param id nft主键
     * @return nft
     */
    public SuiNftRecord selectSuiNftRecordById(Long id);

    /**
     * 查询nft列表
     * 
     * @param suiNftRecord nft
     * @return nft集合
     */
    public List<SuiNftRecord> selectSuiNftRecordList(SuiNftRecord suiNftRecord);

    /**
     * 新增nft
     * 
     * @param suiNftRecord nft
     * @return 结果
     */
    public int insertSuiNftRecord(SuiNftRecord suiNftRecord);

    /**
     * 修改nft
     * 
     * @param suiNftRecord nft
     * @return 结果
     */
    public int updateSuiNftRecord(SuiNftRecord suiNftRecord);

    /**
     * 批量删除nft
     * 
     * @param ids 需要删除的nft主键集合
     * @return 结果
     */
    public int deleteSuiNftRecordByIds(Long[] ids);

    /**
     * 删除nft信息
     * 
     * @param id nft主键
     * @return 结果
     */
    public int deleteSuiNftRecordById(Long id);



    public SuiDataStatics selectSuiDataStatics();
}

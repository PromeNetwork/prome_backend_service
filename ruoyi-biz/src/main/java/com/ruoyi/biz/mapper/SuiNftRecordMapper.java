package com.ruoyi.biz.mapper;

import java.util.List;

import com.ruoyi.biz.domain.Rank;
import com.ruoyi.biz.domain.SuiNftRecord;
import com.ruoyi.biz.domain.TimeWindow;
import com.ruoyi.biz.domain.TotalStatu;

/**
 * nftMapper接口
 * 
 * @author keivn
 * @date 2024-03-27
 */
public interface SuiNftRecordMapper 
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


    public  List<TimeWindow> selectShredByDay();

    public List<TimeWindow> selectShredMintByDay();


    public List<TimeWindow>  selectSwordTxByDay();

    public List<TimeWindow>  selectSwordMintByDay();
    public  List<Rank>  selectRank();


    public List<TotalStatu> selectTotalStatus();

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
     * 删除nft
     * 
     * @param id nft主键
     * @return 结果
     */
    public int deleteSuiNftRecordById(Long id);

    /**
     * 批量删除nft
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSuiNftRecordByIds(Long[] ids);
}

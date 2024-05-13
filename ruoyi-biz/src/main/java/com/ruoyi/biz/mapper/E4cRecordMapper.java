package com.ruoyi.biz.mapper;

import java.util.List;
import com.ruoyi.biz.domain.E4cRecord;

/**
 * e4cMapper接口
 * 
 * @author kevin
 * @date 2024-03-27
 */
public interface E4cRecordMapper 
{
    /**
     * 查询e4c
     * 
     * @param id e4c主键
     * @return e4c
     */
    public E4cRecord selectE4cRecordById(Long id);

    /**
     * 查询e4c列表
     * 
     * @param e4cRecord e4c
     * @return e4c集合
     */
    public List<E4cRecord> selectE4cRecordList(E4cRecord e4cRecord);

    /**
     * 新增e4c
     * 
     * @param e4cRecord e4c
     * @return 结果
     */
    public int insertE4cRecord(E4cRecord e4cRecord);

    /**
     * 修改e4c
     * 
     * @param e4cRecord e4c
     * @return 结果
     */
    public int updateE4cRecord(E4cRecord e4cRecord);

    /**
     * 删除e4c
     * 
     * @param id e4c主键
     * @return 结果
     */
    public int deleteE4cRecordById(Long id);

    /**
     * 批量删除e4c
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteE4cRecordByIds(Long[] ids);
}

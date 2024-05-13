package com.ruoyi.biz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.biz.mapper.E4cRecordMapper;
import com.ruoyi.biz.domain.E4cRecord;
import com.ruoyi.biz.service.IE4cRecordService;

/**
 * e4cService业务层处理
 * 
 * @author kevin
 * @date 2024-03-27
 */
@Service
public class E4cRecordServiceImpl implements IE4cRecordService 
{
    @Autowired
    private E4cRecordMapper e4cRecordMapper;

    /**
     * 查询e4c
     * 
     * @param id e4c主键
     * @return e4c
     */
    @Override
    public E4cRecord selectE4cRecordById(Long id)
    {
        return e4cRecordMapper.selectE4cRecordById(id);
    }

    /**
     * 查询e4c列表
     * 
     * @param e4cRecord e4c
     * @return e4c
     */
    @Override
    public List<E4cRecord> selectE4cRecordList(E4cRecord e4cRecord)
    {
        return e4cRecordMapper.selectE4cRecordList(e4cRecord);
    }

    /**
     * 新增e4c
     * 
     * @param e4cRecord e4c
     * @return 结果
     */
    @Override
    public int insertE4cRecord(E4cRecord e4cRecord)
    {
        return e4cRecordMapper.insertE4cRecord(e4cRecord);
    }

    /**
     * 修改e4c
     * 
     * @param e4cRecord e4c
     * @return 结果
     */
    @Override
    public int updateE4cRecord(E4cRecord e4cRecord)
    {
        return e4cRecordMapper.updateE4cRecord(e4cRecord);
    }

    /**
     * 批量删除e4c
     * 
     * @param ids 需要删除的e4c主键
     * @return 结果
     */
    @Override
    public int deleteE4cRecordByIds(Long[] ids)
    {
        return e4cRecordMapper.deleteE4cRecordByIds(ids);
    }

    /**
     * 删除e4c信息
     * 
     * @param id e4c主键
     * @return 结果
     */
    @Override
    public int deleteE4cRecordById(Long id)
    {
        return e4cRecordMapper.deleteE4cRecordById(id);
    }
}

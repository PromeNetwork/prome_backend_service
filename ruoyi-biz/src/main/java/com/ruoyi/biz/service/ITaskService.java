package com.ruoyi.biz.service;

import java.util.List;
import com.ruoyi.biz.domain.Task;

/**
 * taskService接口
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public interface ITaskService 
{
    /**
     * 查询task
     * 
     * @param id task主键
     * @return task
     */
    public Task selectTaskById(Long id);

    /**
     * 查询task列表
     * 
     * @param task task
     * @return task集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增task
     * 
     * @param task task
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改task
     * 
     * @param task task
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 批量删除task
     * 
     * @param ids 需要删除的task主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    /**
     * 删除task信息
     * 
     * @param id task主键
     * @return 结果
     */
    public int deleteTaskById(Long id);
}

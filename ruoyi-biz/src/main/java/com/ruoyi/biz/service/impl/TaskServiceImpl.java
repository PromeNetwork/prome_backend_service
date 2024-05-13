package com.ruoyi.biz.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

import com.ruoyi.biz.domain.AccountV2;
import com.ruoyi.biz.domain.Coupon;
import com.ruoyi.biz.mapper.AccountV2Mapper;
import com.ruoyi.biz.mapper.CouponMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.biz.mapper.TaskMapper;
import com.ruoyi.biz.domain.Task;
import com.ruoyi.biz.service.ITaskService;

import static java.lang.String.format;

/**
 * taskService业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-10
 */

@Service
public class TaskServiceImpl implements ITaskService 
{

    Logger logger = Logger.getLogger(TaskServiceImpl.class.getName());
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private AccountV2Mapper accountV2Mapper;
    /**
     * 查询task
     * 
     * @param id task主键
     * @return task
     */
    @Override
    public Task selectTaskById(Long id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询task列表
     * 
     * @param task task
     * @return task
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增task
     * 
     * @param task task
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        task.setCreateTime(DateUtils.getNowDate());
        return taskMapper.insertTask(task);
    }

    /**
     * 修改task
     * 
     * @param task task
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
         int res= taskMapper.updateTask(task);
        if(Objects.equals(task.getStatus(), "complete")){
            Task queryTask= new Task();
            queryTask.setAddress(task.getAddress());
            queryTask.setStatus("complete");
            List<Task> taskList = selectTaskList(queryTask);
            if(taskList.size()==5){
                Coupon coupon =new Coupon();
                coupon.setAddress(task.getAddress());
                coupon.setCode(UUID.randomUUID().toString());
                coupon.setStatus(0L);
                coupon.setSerial(1L);
                try {
                    couponMapper.insertCoupon(coupon);
                }catch (Exception e){
                    logger.info(format("insert coupon error %s",coupon));
                }
                AccountV2 accountQuery =new AccountV2();
                accountQuery.setAddress(task.getAddress());
                List<AccountV2> ac = accountV2Mapper.selectAccountV2List(accountQuery);
                if(ac.size()!=1) {
                    return res;
                }
                AccountV2 account = ac.get(0);
                AccountV2 parentQuery =new AccountV2();
                parentQuery.setAddress(account.getParent());
                List<AccountV2> parentList = accountV2Mapper.selectAccountV2List(parentQuery);
                if(parentList.size()!=1) {
                    return res;
                }
                AccountV2 parent = parentList.get(0);
                Coupon coupon2 =new Coupon();
                coupon2.setAddress(parent.getAddress());
                coupon2.setCode(UUID.randomUUID().toString());
                coupon2.setStatus(0L);
                coupon2.setSerial(2L);
                try {
                    couponMapper.insertCoupon(coupon2);
                }catch (Exception e){
                    logger.info(format("insert coupon2 error %s",coupon2));
                }
                AccountV2 accountParentUpdate =new AccountV2();
                accountParentUpdate.setId(parent.getId());
                accountParentUpdate.setTaskCount(parent.getTaskCount()+1);
                accountV2Mapper.updateAccountV2(accountParentUpdate);
                //父地址插入last task complete
                Task lastTask= new Task();
                lastTask.setAddress(parent.getAddress());
                lastTask.setStatus("complete");
                lastTask.setCreateTime(DateUtils.getNowDate());
                lastTask.setType("SHARE");
                taskMapper.insertTask(lastTask);
            }
        }
        return res;
    }

    /**
     * 批量删除task
     * 
     * @param ids 需要删除的task主键
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除task信息
     * 
     * @param id task主键
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id)
    {
        return taskMapper.deleteTaskById(id);
    }
}

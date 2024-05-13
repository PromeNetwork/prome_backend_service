package com.ruoyi.biz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.biz.mapper.CouponMapper;
import com.ruoyi.biz.domain.Coupon;
import com.ruoyi.biz.service.ICouponService;

/**
 * couponService业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@Service
public class CouponServiceImpl implements ICouponService 
{
    @Autowired
    private CouponMapper couponMapper;

    /**
     * 查询coupon
     * 
     * @param id coupon主键
     * @return coupon
     */
    @Override
    public Coupon selectCouponById(Long id)
    {
        return couponMapper.selectCouponById(id);
    }

    /**
     * 查询coupon列表
     * 
     * @param coupon coupon
     * @return coupon
     */
    @Override
    public List<Coupon> selectCouponList(Coupon coupon)
    {
        return couponMapper.selectCouponList(coupon);
    }

    /**
     * 新增coupon
     * 
     * @param coupon coupon
     * @return 结果
     */
    @Override
    public int insertCoupon(Coupon coupon)
    {
        coupon.setCreateTime(DateUtils.getNowDate());
        return couponMapper.insertCoupon(coupon);
    }

    /**
     * 修改coupon
     * 
     * @param coupon coupon
     * @return 结果
     */
    @Override
    public int updateCoupon(Coupon coupon)
    {
        return couponMapper.updateCoupon(coupon);
    }

    /**
     * 批量删除coupon
     * 
     * @param ids 需要删除的coupon主键
     * @return 结果
     */
    @Override
    public int deleteCouponByIds(Long[] ids)
    {
        return couponMapper.deleteCouponByIds(ids);
    }

    /**
     * 删除coupon信息
     * 
     * @param id coupon主键
     * @return 结果
     */
    @Override
    public int deleteCouponById(Long id)
    {
        return couponMapper.deleteCouponById(id);
    }
}

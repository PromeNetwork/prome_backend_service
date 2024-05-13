package com.ruoyi.biz.service;

import java.util.List;
import com.ruoyi.biz.domain.Coupon;

/**
 * couponService接口
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public interface ICouponService 
{
    /**
     * 查询coupon
     * 
     * @param id coupon主键
     * @return coupon
     */
    public Coupon selectCouponById(Long id);

    /**
     * 查询coupon列表
     * 
     * @param coupon coupon
     * @return coupon集合
     */
    public List<Coupon> selectCouponList(Coupon coupon);

    /**
     * 新增coupon
     * 
     * @param coupon coupon
     * @return 结果
     */
    public int insertCoupon(Coupon coupon);

    /**
     * 修改coupon
     * 
     * @param coupon coupon
     * @return 结果
     */
    public int updateCoupon(Coupon coupon);

    /**
     * 批量删除coupon
     * 
     * @param ids 需要删除的coupon主键集合
     * @return 结果
     */
    public int deleteCouponByIds(Long[] ids);

    /**
     * 删除coupon信息
     * 
     * @param id coupon主键
     * @return 结果
     */
    public int deleteCouponById(Long id);
}
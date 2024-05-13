package com.ruoyi.biz.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.biz.domain.Coupon;
import com.ruoyi.biz.service.ICouponService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * couponController
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/biz/coupon")
public class CouponController extends BaseController
{
    @Autowired
    private ICouponService couponService;

    /**
     * 查询coupon列表
     */
    @PreAuthorize("@ss.hasPermi('biz:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(Coupon coupon)
    {
        startPage();
        List<Coupon> list = couponService.selectCouponList(coupon);
        return getDataTable(list);
    }

    /**
     * 导出coupon列表
     */
    @PreAuthorize("@ss.hasPermi('biz:coupon:export')")
    @Log(title = "coupon", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Coupon coupon)
    {
        List<Coupon> list = couponService.selectCouponList(coupon);
        ExcelUtil<Coupon> util = new ExcelUtil<Coupon>(Coupon.class);
        util.exportExcel(response, list, "coupon数据");
    }

    /**
     * 获取coupon详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(couponService.selectCouponById(id));
    }

    /**
     * 新增coupon
     */
    @PreAuthorize("@ss.hasPermi('biz:coupon:add')")
    @Log(title = "coupon", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Coupon coupon)
    {
        return toAjax(couponService.insertCoupon(coupon));
    }

    /**
     * 修改coupon
     */
    @PreAuthorize("@ss.hasPermi('biz:coupon:edit')")
    @Log(title = "coupon", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Coupon coupon)
    {
        return toAjax(couponService.updateCoupon(coupon));
    }

    /**
     * 删除coupon
     */
    @PreAuthorize("@ss.hasPermi('biz:coupon:remove')")
    @Log(title = "coupon", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(couponService.deleteCouponByIds(ids));
    }
}

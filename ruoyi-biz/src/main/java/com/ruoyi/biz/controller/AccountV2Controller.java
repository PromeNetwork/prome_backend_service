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
import com.ruoyi.biz.domain.AccountV2;
import com.ruoyi.biz.service.IAccountV2Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * accountController
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/biz/account")
public class AccountV2Controller extends BaseController
{
    @Autowired
    private IAccountV2Service accountV2Service;

    /**
     * 查询account列表
     */
    @PreAuthorize("@ss.hasPermi('biz:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(AccountV2 accountV2)
    {
        startPage();
        List<AccountV2> list = accountV2Service.selectAccountV2List(accountV2);
        return getDataTable(list);
    }

    /**
     * 导出account列表
     */
    @PreAuthorize("@ss.hasPermi('biz:account:export')")
    @Log(title = "account", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccountV2 accountV2)
    {
        List<AccountV2> list = accountV2Service.selectAccountV2List(accountV2);
        ExcelUtil<AccountV2> util = new ExcelUtil<AccountV2>(AccountV2.class);
        util.exportExcel(response, list, "account数据");
    }

    /**
     * 获取account详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz:account:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(accountV2Service.selectAccountV2ById(id));
    }

    /**
     * 新增account
     */
    @PreAuthorize("@ss.hasPermi('biz:account:add')")
    @Log(title = "account", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccountV2 accountV2)
    {
        return toAjax(accountV2Service.insertAccountV2(accountV2));
    }

    /**
     * 修改account
     */
    @PreAuthorize("@ss.hasPermi('biz:account:edit')")
    @Log(title = "account", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccountV2 accountV2)
    {
        return toAjax(accountV2Service.updateAccountV2(accountV2));
    }

    /**
     * 删除account
     */
    @PreAuthorize("@ss.hasPermi('biz:account:remove')")
    @Log(title = "account", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accountV2Service.deleteAccountV2ByIds(ids));
    }
}

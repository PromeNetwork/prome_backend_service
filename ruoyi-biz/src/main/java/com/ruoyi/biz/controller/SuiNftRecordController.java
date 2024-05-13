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

import com.ruoyi.biz.domain.TimeWindow;
import com.ruoyi.biz.vi.SuiDataStatics;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.biz.domain.SuiNftRecord;
import com.ruoyi.biz.service.ISuiNftRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * nftController
 * 
 * @author keivn
 * @date 2024-03-27
 */
@RestController
@RequestMapping("/biz/nft")
public class SuiNftRecordController extends BaseController
{
    @Autowired
    private ISuiNftRecordService suiNftRecordService;

    /**
     * 查询nft列表
     */
    @PreAuthorize("@ss.hasPermi('biz:nft:list')")
    @GetMapping("/list")
    public TableDataInfo list(SuiNftRecord suiNftRecord)
    {
        startPage();
        if(suiNftRecord.getReceiver() != null && !suiNftRecord.getReceiver().isEmpty()){
            suiNftRecord.setReceiver(suiNftRecord.getReceiver().toLowerCase());
        }
        List<SuiNftRecord> list = suiNftRecordService.selectSuiNftRecordList(suiNftRecord);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('biz:nft:list')")
    @GetMapping("/shredByDay")
    public SuiDataStatics selectShredByDay()
    {
        return suiNftRecordService.selectSuiDataStatics();
    }

    /**
     * 导出nft列表
     */
    @PreAuthorize("@ss.hasPermi('biz:nft:export')")
    @Log(title = "nft", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SuiNftRecord suiNftRecord)
    {
        List<SuiNftRecord> list = suiNftRecordService.selectSuiNftRecordList(suiNftRecord);
        ExcelUtil<SuiNftRecord> util = new ExcelUtil<SuiNftRecord>(SuiNftRecord.class);
        util.exportExcel(response, list, "nft数据");
    }

    /**
     * 获取nft详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz:nft:query')")
    @GetMapping(value = "/nfts/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(suiNftRecordService.selectSuiNftRecordById(id));
    }

    /**
     * 新增nft
     */
    @PreAuthorize("@ss.hasPermi('biz:nft:add')")
    @Log(title = "nft", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SuiNftRecord suiNftRecord)
    {
        return toAjax(suiNftRecordService.insertSuiNftRecord(suiNftRecord));
    }

    /**
     * 修改nft
     */
    @PreAuthorize("@ss.hasPermi('biz:nft:edit')")
    @Log(title = "nft", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SuiNftRecord suiNftRecord)
    {
        return toAjax(suiNftRecordService.updateSuiNftRecord(suiNftRecord));
    }

    /**
     * 删除nft
     */
    @PreAuthorize("@ss.hasPermi('biz:nft:remove')")
    @Log(title = "nft", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(suiNftRecordService.deleteSuiNftRecordByIds(ids));
    }
}

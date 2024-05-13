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
import com.ruoyi.biz.domain.E4cRecord;
import com.ruoyi.biz.service.IE4cRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * e4cController
 * 
 * @author kevin
 * @date 2024-03-27
 */
@RestController
@RequestMapping("/biz/e4c")
public class E4cRecordController extends BaseController
{
    @Autowired
    private IE4cRecordService e4cRecordService;

    /**
     * 查询e4c列表
     */
    @PreAuthorize("@ss.hasPermi('biz:e4c:list')")
    @GetMapping("/list")
    public TableDataInfo list(E4cRecord e4cRecord)
    {
        startPage();
        if(e4cRecord.getRecipient() != null && !e4cRecord.getRecipient().isEmpty()){
            e4cRecord.setRecipient(e4cRecord.getRecipient().toLowerCase());
        }
        List<E4cRecord> list = e4cRecordService.selectE4cRecordList(e4cRecord);
        return getDataTable(list);
    }

    /**
     * 导出e4c列表
     */
    @PreAuthorize("@ss.hasPermi('biz:e4c:export')")
    @Log(title = "e4c", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, E4cRecord e4cRecord)
    {
        List<E4cRecord> list = e4cRecordService.selectE4cRecordList(e4cRecord);
        ExcelUtil<E4cRecord> util = new ExcelUtil<E4cRecord>(E4cRecord.class);
        util.exportExcel(response, list, "e4c数据");
    }

    /**
     * 获取e4c详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz:e4c:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(e4cRecordService.selectE4cRecordById(id));
    }

    /**
     * 新增e4c
     */
    @PreAuthorize("@ss.hasPermi('biz:e4c:add')")
    @Log(title = "e4c", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody E4cRecord e4cRecord)
    {
        return toAjax(e4cRecordService.insertE4cRecord(e4cRecord));
    }

    /**
     * 修改e4c
     */
    @PreAuthorize("@ss.hasPermi('biz:e4c:edit')")
    @Log(title = "e4c", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody E4cRecord e4cRecord)
    {
        return toAjax(e4cRecordService.updateE4cRecord(e4cRecord));
    }

    /**
     * 删除e4c
     */
    @PreAuthorize("@ss.hasPermi('biz:e4c:remove')")
    @Log(title = "e4c", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(e4cRecordService.deleteE4cRecordByIds(ids));
    }
}

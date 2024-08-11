package com.ruoyi.shuai.controller;

import java.util.List;
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
import com.ruoyi.shuai.domain.HuiSoftware;
import com.ruoyi.shuai.service.IHuiSoftwareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 应用信息Controller
 * 
 * @author coderH
 * @date 2023-03-30
 */
@RestController
@RequestMapping("/shuai/software")
public class HuiSoftwareController extends BaseController
{
    @Autowired
    private IHuiSoftwareService huiSoftwareService;

    /**
     * 查询应用信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HuiSoftware huiSoftware)
    {
        startPage();
        List<HuiSoftware> list = huiSoftwareService.selectHuiSoftwareList(huiSoftware);
        return getDataTable(list);
    }

    /**
     * 导出应用信息列表
     */
    @Log(title = "应用信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(HuiSoftware huiSoftware)
    {
        List<HuiSoftware> list = huiSoftwareService.selectHuiSoftwareList(huiSoftware);
        ExcelUtil<HuiSoftware> util = new ExcelUtil<HuiSoftware>(HuiSoftware.class);
        return util.exportExcel(list, "software");
    }

    /**
     * 获取应用信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(huiSoftwareService.selectHuiSoftwareById(id));
    }

    /**
     * 新增应用信息
     */
    @Log(title = "应用信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HuiSoftware huiSoftware)
    {
        return toAjax(huiSoftwareService.insertHuiSoftware(huiSoftware));
    }

    /**
     * 修改应用信息
     */
    @Log(title = "应用信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HuiSoftware huiSoftware)
    {
        return toAjax(huiSoftwareService.updateHuiSoftware(huiSoftware));
    }

    /**
     * 删除应用信息
     */
    @Log(title = "应用信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(huiSoftwareService.deleteHuiSoftwareByIds(ids));
    }
}

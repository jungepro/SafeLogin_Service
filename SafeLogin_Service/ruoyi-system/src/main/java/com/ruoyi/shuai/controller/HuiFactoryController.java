package com.ruoyi.shuai.controller;

import java.util.List;

import com.ruoyi.common.core.redis.RedisCache;
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
import com.ruoyi.shuai.domain.HuiFactory;
import com.ruoyi.shuai.service.IHuiFactoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 厂家信息Controller
 *
 * @author coderH
 * @date 2023-03-30
 */
@RestController
@RequestMapping("/shuai/factory")
public class HuiFactoryController extends BaseController {
    @Autowired
    private IHuiFactoryService huiFactoryService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询厂家信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HuiFactory huiFactory) {
        startPage();
        List<HuiFactory> list = huiFactoryService.selectHuiFactoryList(huiFactory);
        return getDataTable(list);
    }

    /**
     * 导出厂家信息列表
     */
    @Log(title = "厂家信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(HuiFactory huiFactory) {
        List<HuiFactory> list = huiFactoryService.selectHuiFactoryList(huiFactory);
        ExcelUtil<HuiFactory> util = new ExcelUtil<HuiFactory>(HuiFactory.class);
        return util.exportExcel(list, "factory");
    }

    /**
     * 获取厂家信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(huiFactoryService.selectHuiFactoryById(id));
    }

    /**
     * 新增厂家信息
     */
    @Log(title = "厂家信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HuiFactory huiFactory) {
        return toAjax(huiFactoryService.insertHuiFactory(huiFactory));
    }

    /**
     * 修改厂家信息
     */
    @Log(title = "厂家信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HuiFactory huiFactory) {
        redisCache.deleteObject(huiFactory.getAccount());
        return toAjax(huiFactoryService.updateHuiFactory(huiFactory));
    }

    /**
     * 删除厂家信息
     */
    @Log(title = "厂家信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(huiFactoryService.deleteHuiFactoryByIds(ids));
    }
}

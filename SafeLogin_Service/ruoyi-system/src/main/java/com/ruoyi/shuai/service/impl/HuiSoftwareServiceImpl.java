package com.ruoyi.shuai.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shuai.mapper.HuiSoftwareMapper;
import com.ruoyi.shuai.domain.HuiSoftware;
import com.ruoyi.shuai.service.IHuiSoftwareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 应用信息Service业务层处理
 * 
 * @author coderH
 * @date 2023-03-30
 */
@Service
public class HuiSoftwareServiceImpl extends ServiceImpl<HuiSoftwareMapper, HuiSoftware> implements IHuiSoftwareService
{
    @Autowired
    private HuiSoftwareMapper huiSoftwareMapper;

    /**
     * 查询应用信息
     * 
     * @param id 应用信息ID
     * @return 应用信息
     */
    @Override
    public HuiSoftware selectHuiSoftwareById(Integer id)
    {
        return huiSoftwareMapper.selectHuiSoftwareById(id);
    }

    /**
     * 查询应用信息列表
     * 
     * @param huiSoftware 应用信息
     * @return 应用信息
     */
    @Override
    public List<HuiSoftware> selectHuiSoftwareList(HuiSoftware huiSoftware)
    {
        return huiSoftwareMapper.selectHuiSoftwareList(huiSoftware);
    }

    /**
     * 新增应用信息
     * 
     * @param huiSoftware 应用信息
     * @return 结果
     */
    @Override
    public int insertHuiSoftware(HuiSoftware huiSoftware)
    {
        return huiSoftwareMapper.insertHuiSoftware(huiSoftware);
    }

    /**
     * 修改应用信息
     * 
     * @param huiSoftware 应用信息
     * @return 结果
     */
    @Override
    public int updateHuiSoftware(HuiSoftware huiSoftware)
    {
        return huiSoftwareMapper.updateHuiSoftware(huiSoftware);
    }

    /**
     * 批量删除应用信息
     * 
     * @param ids 需要删除的应用信息ID
     * @return 结果
     */
    @Override
    public int deleteHuiSoftwareByIds(Integer[] ids)
    {
        return huiSoftwareMapper.deleteHuiSoftwareByIds(ids);
    }

    /**
     * 删除应用信息信息
     * 
     * @param id 应用信息ID
     * @return 结果
     */
    @Override
    public int deleteHuiSoftwareById(Integer id)
    {
        return huiSoftwareMapper.deleteHuiSoftwareById(id);
    }
}

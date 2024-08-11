package com.ruoyi.shuai.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shuai.mapper.HuiFactoryMapper;
import com.ruoyi.shuai.domain.HuiFactory;
import com.ruoyi.shuai.service.IHuiFactoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 厂家信息Service业务层处理
 *
 * @author coderH
 * @date 2023-03-30
 */
@Service
public class HuiFactoryServiceImpl extends ServiceImpl<HuiFactoryMapper, HuiFactory> implements IHuiFactoryService
{
    @Autowired
    private HuiFactoryMapper huiFactoryMapper;

    /**
     * 查询厂家信息
     *
     * @param id 厂家信息ID
     * @return 厂家信息
     */
    @Override
    public HuiFactory selectHuiFactoryById(Integer id)
    {
        return huiFactoryMapper.selectHuiFactoryById(id);
    }

    /**
     * 查询厂家信息列表
     *
     * @param huiFactory 厂家信息
     * @return 厂家信息
     */
    @Override
    public List<HuiFactory> selectHuiFactoryList(HuiFactory huiFactory)
    {
        return huiFactoryMapper.selectHuiFactoryList(huiFactory);
    }

    /**
     * 新增厂家信息
     *
     * @param huiFactory 厂家信息
     * @return 结果
     */
    @Override
    public int insertHuiFactory(HuiFactory huiFactory)
    {
        return huiFactoryMapper.insertHuiFactory(huiFactory);
    }

    /**
     * 修改厂家信息
     *
     * @param huiFactory 厂家信息
     * @return 结果
     */
    @Override
    public int updateHuiFactory(HuiFactory huiFactory)
    {
        return huiFactoryMapper.updateHuiFactory(huiFactory);
    }

    /**
     * 批量删除厂家信息
     *
     * @param ids 需要删除的厂家信息ID
     * @return 结果
     */
    @Override
    public int deleteHuiFactoryByIds(Integer[] ids)
    {
        return huiFactoryMapper.deleteHuiFactoryByIds(ids);
    }

    /**
     * 删除厂家信息信息
     *
     * @param id 厂家信息ID
     * @return 结果
     */
    @Override
    public int deleteHuiFactoryById(Integer id)
    {
        return huiFactoryMapper.deleteHuiFactoryById(id);
    }

    @Override
    public void expireData(String username, int i) {
        LambdaUpdateWrapper<HuiFactory> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(HuiFactory::getAccount,username);
        updateWrapper.set(HuiFactory::getZt,i);
        this.update(updateWrapper);
    }
}

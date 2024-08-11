package com.ruoyi.shuai.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.shuai.domain.HuiFactory;

/**
 * 厂家信息Mapper接口
 *
 * @author coderH
 * @date 2023-03-30
 */
public interface HuiFactoryMapper extends BaseMapper<HuiFactory>
{
    /**
     * 查询厂家信息
     *
     * @param id 厂家信息ID
     * @return 厂家信息
     */
    public HuiFactory selectHuiFactoryById(Integer id);

    /**
     * 查询厂家信息列表
     *
     * @param huiFactory 厂家信息
     * @return 厂家信息集合
     */
    public List<HuiFactory> selectHuiFactoryList(HuiFactory huiFactory);

    /**
     * 新增厂家信息
     *
     * @param huiFactory 厂家信息
     * @return 结果
     */
    public int insertHuiFactory(HuiFactory huiFactory);

    /**
     * 修改厂家信息
     *
     * @param huiFactory 厂家信息
     * @return 结果
     */
    public int updateHuiFactory(HuiFactory huiFactory);

    /**
     * 删除厂家信息
     *
     * @param id 厂家信息ID
     * @return 结果
     */
    public int deleteHuiFactoryById(Integer id);

    /**
     * 批量删除厂家信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHuiFactoryByIds(Integer[] ids);
}

package com.ruoyi.shuai.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.shuai.domain.HuiFactory;

/**
 * 厂家信息Service接口
 *
 * @author coderH
 * @date 2023-03-30
 */
public interface IHuiFactoryService extends IService<HuiFactory>
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
     * 批量删除厂家信息
     *
     * @param ids 需要删除的厂家信息ID
     * @return 结果
     */
    public int deleteHuiFactoryByIds(Integer[] ids);

    /**
     * 删除厂家信息信息
     *
     * @param id 厂家信息ID
     * @return 结果
     */
    public int deleteHuiFactoryById(Integer id);

    void expireData(String username, int i);
}

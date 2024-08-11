package com.ruoyi.shuai.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.shuai.domain.HuiSoftware;

/**
 * 应用信息Mapper接口
 *
 * @author coderH
 * @date 2023-03-30
 */
public interface HuiSoftwareMapper extends BaseMapper<HuiSoftware>
{
    /**
     * 查询应用信息
     *
     * @param id 应用信息ID
     * @return 应用信息
     */
    public HuiSoftware selectHuiSoftwareById(Integer id);

    /**
     * 查询应用信息列表
     *
     * @param huiSoftware 应用信息
     * @return 应用信息集合
     */
    public List<HuiSoftware> selectHuiSoftwareList(HuiSoftware huiSoftware);

    /**
     * 新增应用信息
     *
     * @param huiSoftware 应用信息
     * @return 结果
     */
    public int insertHuiSoftware(HuiSoftware huiSoftware);

    /**
     * 修改应用信息
     *
     * @param huiSoftware 应用信息
     * @return 结果
     */
    public int updateHuiSoftware(HuiSoftware huiSoftware);

    /**
     * 删除应用信息
     *
     * @param id 应用信息ID
     * @return 结果
     */
    public int deleteHuiSoftwareById(Integer id);

    /**
     * 批量删除应用信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHuiSoftwareByIds(Integer[] ids);
}

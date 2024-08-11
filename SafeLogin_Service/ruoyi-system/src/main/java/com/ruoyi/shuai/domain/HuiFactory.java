package com.ruoyi.shuai.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 厂家信息对象 hui_factory
 *
 * @author coderH
 * @date 2023-03-30
 */
@TableName("hui_factory")
public class HuiFactory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 登录次数 */
    @Excel(name = "登录次数")
    private Integer maxlogin;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ip;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiretime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer zt;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAccount()
    {
        return account;
    }
    public void setMaxlogin(Integer maxlogin)
    {
        this.maxlogin = maxlogin;
    }

    public Integer getMaxlogin()
    {
        return maxlogin;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getIp()
    {
        return ip;
    }
    public void setExpiretime(Date expiretime)
    {
        this.expiretime = expiretime;
    }

    public Date getExpiretime()
    {
        return expiretime;
    }
    public void setZt(Integer zt)
    {
        this.zt = zt;
    }

    public Integer getZt()
    {
        return zt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("account", getAccount())
            .append("maxlogin", getMaxlogin())
            .append("ip", getIp())
            .append("expiretime", getExpiretime())
            .append("zt", getZt())
            .toString();
    }
}

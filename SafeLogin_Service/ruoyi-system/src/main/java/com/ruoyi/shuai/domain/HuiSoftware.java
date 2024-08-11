package com.ruoyi.shuai.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应用信息对象 hui_software
 *
 * @author coderH
 * @date 2023-03-30
 */
@TableName("hui_software")
public class HuiSoftware extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 厂家名 */
    @Excel(name = "厂家名")
    private String name;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 应用名 */
    @Excel(name = "应用名")
    private String swname;

    /** 封面 */
    @Excel(name = "封面")
    private String cover;

    /** 应用地址 */
    @Excel(name = "应用地址")
    private String url;

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
    public void setSwname(String swname)
    {
        this.swname = swname;
    }

    public String getSwname()
    {
        return swname;
    }
    public void setCover(String cover)
    {
        this.cover = cover;
    }

    public String getCover()
    {
        return cover;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("account", getAccount())
            .append("swname", getSwname())
            .append("cover", getCover())
            .append("url", getUrl())
            .toString();
    }
}

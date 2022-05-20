package com.cdf.mall.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Description 系统参数配置实体
 * @Author hanyaguang
 * @Date 2022/5/20 14:01
 * @Version 1.0
 */
@TableName("setting")
public class SystemSetting {
    private String settingKey;
    private String settingValue;
    private Date createTime;
    private Date updateTime;
    private String remark;

    public SystemSetting() {
    }

    public String getSettingKey() {
        return this.settingKey;
    }

    public String getSettingValue() {
        return this.settingValue;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setSettingKey(final String settingKey) {
        this.settingKey = settingKey;
    }

    public void setSettingValue(final String settingValue) {
        this.settingValue = settingValue;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }
}

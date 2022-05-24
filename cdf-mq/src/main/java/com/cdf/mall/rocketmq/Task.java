package com.cdf.mall.rocketmq;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

/**
 * @Description 任务父类
 * @Author hanyaguang
 * @Date 2022/5/24 15:55
 * @Version 1.0
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class"
)
public class Task {
    protected String id;
    protected String name;
    protected String data;
    protected Date firstTriggerTime;
    protected Date nextTriggerTime;
    protected String error;
    protected Date createTime;
    protected Date updateTime;
    private String cron;
    private String timeZone;

    public Task() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getData() {
        return this.data;
    }

    public Date getFirstTriggerTime() {
        return this.firstTriggerTime;
    }

    public Date getNextTriggerTime() {
        return this.nextTriggerTime;
    }

    public String getError() {
        return this.error;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getCron() {
        return this.cron;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public void setFirstTriggerTime(final Date firstTriggerTime) {
        this.firstTriggerTime = firstTriggerTime;
    }

    public void setNextTriggerTime(final Date nextTriggerTime) {
        this.nextTriggerTime = nextTriggerTime;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCron(final String cron) {
        this.cron = cron;
    }

    public void setTimeZone(final String timeZone) {
        this.timeZone = timeZone;
    }
}

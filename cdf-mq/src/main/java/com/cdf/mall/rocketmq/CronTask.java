package com.cdf.mall.rocketmq;

/**
 * @Description 定时任务，通过数据库配置任务参数进行消息发送处理
 * @Author hanyaguang
 * @Date 2022/5/30 16:48
 * @Version 1.0
 */
public class CronTask extends Task{
    private String cron;
    private String timeZone;

    public CronTask() {
    }

    public String getCron() {
        return this.cron;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setCron(final String cron) {
        this.cron = cron;
    }

    public void setTimeZone(final String timeZone) {
        this.timeZone = timeZone;
    }
}

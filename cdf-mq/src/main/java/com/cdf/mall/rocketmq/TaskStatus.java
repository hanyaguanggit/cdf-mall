package com.cdf.mall.rocketmq;

/**
 * @Description 任务类型枚举
 * @Author hanyaguang
 * @Date 2022/5/24 15:57
 * @Version 1.0
 */
public enum  TaskStatus {
    INIT,
    SCHEDULING,
    FINISH,
    FAILED;

    private TaskStatus() {
    }
}

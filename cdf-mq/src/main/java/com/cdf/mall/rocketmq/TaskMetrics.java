package com.cdf.mall.rocketmq;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Description 任务计数器
 * @Author hanyaguang
 * @Date 2022/5/24 16:13
 * @Version 1.0
 */
public class TaskMetrics {
    private static final Logger log = LoggerFactory.getLogger(TaskMetrics.class);
    private static MeterRegistry registry;

    public TaskMetrics(MeterRegistry registry) {
        TaskMetrics.registry = registry;
    }

    public static void duration(String name, long duration) {
        registry.timer("task", new String[]{"name", name}).record(duration, TimeUnit.MILLISECONDS);
    }

    public static void failed(String name) {
        registry.counter("task_failed", new String[]{"name", name}).increment();
    }
}

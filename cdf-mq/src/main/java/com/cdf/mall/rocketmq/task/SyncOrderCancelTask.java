package com.cdf.mall.rocketmq.task;

import com.alibaba.fastjson.JSON;

import com.cdf.mall.rocketmq.AbstractTask;
import com.cdf.mall.rocketmq.Task;
import com.cdf.mall.service.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SyncOrderCancelTask extends AbstractTask {
    @Autowired
    private PushService pushService;
    @Override
    public String getType() {
        return SyncOrderCancelTask.class.getName();
    }

    @Override
    public boolean isProcessed(Task t) {
        return false;
    }

    @Override
    public void invoke(Task t) {
        log.info("dael==:{}", JSON.toJSONString(t));
        pushService.syncCancel(t.getData());
    }
}

package com.cdf.mall.rocketmq.task;

import com.cdf.mall.rocketmq.AbstractTask;
import com.cdf.mall.rocketmq.Task;
import com.cdf.mall.rocketmq.TaskScheduler;
import com.cdf.mall.service.TraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统事件收到订单取消的消息，再执行任务发送下游。
 */
@Component
@Slf4j
public class SystemEventOrderCancelTask extends AbstractTask {
    @Autowired
    private TraceService traceService;
   /* @Autowired
    private EventTraceLogDao eventTraceLogDao;*/
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private InfrastructureFacade infrastructureFacade;
    @Override
    public String getType() {
        return SystemEventOrderCancelTask.class.getName();
    }

    @Override
    public boolean isProcessed(Task t) {
        return false;
    }

    @Override
    public void invoke(Task t) {
       /* OrderDetailParam orderDetailParam = new OrderDetailParam();
        orderDetailParam.setMainOrderId(t.getData());
        OrderDetailResult orderDetailResult = infrastructureFacade.getOrderDetail(orderDetailParam);
        if(!traceService.checkTrace(orderDetailResult)){
            return;
        }
        String id = t.getId()+"_distribution";
        OnceTask taskNew = new OnceTask();
        taskNew.setId(id);
        taskNew.setName(SyncOrderCancelTask.class.getName());
        taskNew.setData(t.getData());
        taskNew.setType(1);
        taskNew.setIndex1(orderDetailResult.getMainOrderId());
        taskNew.setIndex2(orderDetailResult.getPhone());
        if(!taskScheduler.exist(taskNew)){
            taskScheduler.schedule(taskNew);
        }*/
    }
}

package com.cdf.mall.service;

import java.text.SimpleDateFormat;

/**
 * @Description 推送服务层
 * @Author hanyaguang
 * @Date 2022/5/30 17:08
 * @Version 1.0
 */
public class PushService {

    public void syncCancel(String orderId){
        /*OrderStateChangeRequest orderStateChangeRequest = new OrderStateChangeRequest();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderDetailParam orderDetailParam = new OrderDetailParam();
        orderDetailParam.setMainOrderId(orderId);
        OrderDetailResult orderDetailResult = infrastructureFacade.getOrderDetail(orderDetailParam);
        if(orderDetailResult==null||orderDetailResult.getPayId()==null||orderDetailResult.getPayId().equals("")){
            return;
        }
        String time = format0.format(orderDetailResult.getCancelTime());
        orderStateChangeRequest.setOperateTime(time);
        orderStateChangeRequest.setOrderNo(orderId);
        orderStateChangeRequest.setOrderStatus(6);//对应SyncOrderCancelTask任务
        Result result = HttpRequestUtil.post(getFullPath(REQUEST_URL_ORDER_STATE_CHANGE),orderStateChangeRequest);
        if(!result.isSuccess()){
            TaskErrorUtil.newTaskError(orderStateChangeRequest,result);
        }*/
    }
}

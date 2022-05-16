package com.cdf.mall.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description 测试
 * @Author hanyaguang
 * @Date 2022/5/11 9:42
 * @Version 1.0
 */
///@Component
public class Test {
    public static void main(String[] args) {
        BigDecimal couponAmount = new BigDecimal(0);
        couponAmount = new BigDecimal("12.324");
        System.out.println(couponAmount);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listener(){
        System.out.println("监听到程序启动完成。");
    }
}

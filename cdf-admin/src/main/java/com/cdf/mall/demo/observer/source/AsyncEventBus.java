package com.cdf.mall.demo.observer.source;

import java.util.concurrent.Executor;

/**
 * @Description 异步非阻塞的观察者
 *
 * 为了实现异步非阻塞的观察者模式，它就不能再继续使用 MoreExecutors.directExecutor() 了，
 * 而是需要在构造函数中，由调用者注入线程池。
 *
 * @Author hanyaguang
 * @Date 2022/3/25 17:37
 * @Version 1.0
 */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}

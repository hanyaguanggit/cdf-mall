package com.cdf.mall.zookeeper.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Description zookeeper 互斥锁
 * @Author hanyaguang
 * @Date 2022/5/26 11:26
 * @Version 1.0
 */
public class Lock {
    private static final Logger log = LoggerFactory.getLogger(Lock.class);
    private InterProcessMutex lock;

    private Lock(InterProcessMutex lock) {
        this.lock = lock;
    }

    public boolean tryLock(long time, TimeUnit unit) throws Exception {
        return this.lock.acquire(time, unit);
    }

    public void unlock() {
        if (this.lock.isAcquiredInThisProcess()) {
            try {
                this.lock.release();
            } catch (Exception var2) {
                log.error("unlock error", var2);
            }
        }

    }

    public static Lock create(String type, String key) {
        InterProcessMutex lock = LockFactory.create(type, key);
        return new Lock(lock);
    }
}

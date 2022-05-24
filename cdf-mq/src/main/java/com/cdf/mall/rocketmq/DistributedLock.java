package com.cdf.mall.rocketmq;

import com.cdf.mall.util.GUID;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Description 分销锁
 * @Author hanyaguang
 * @Date 2022/5/24 16:07
 * @Version 1.0
 */
public class DistributedLock {
    private static final Logger log = LoggerFactory.getLogger(DistributedLock.class);
    private byte[] data;
    private final CuratorFramework curatorFramework;
    private final String lock;

    public DistributedLock(CuratorFramework curatorFramework, String lock) {
        this.curatorFramework = curatorFramework;
        this.lock = lock;
    }

    public boolean lock() {
        try {
            this.data = GUID.get().getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException var3) {
            log.error("Unsupported encoding: ISO-8859-1", var3);
            return false;
        }

        try {
            ((ACLBackgroundPathAndBytesable)this.curatorFramework.create().withMode(CreateMode.EPHEMERAL)).forPath(this.lock, this.data);
            return true;
        } catch (Exception var2) {
            log.error("Lock failed. Failed to create zookeeper node [{}]", this.lock, var2);
            return false;
        }
    }

    public void unlock() {
        byte[] bytes;
        try {
            bytes = (byte[])this.curatorFramework.getData().forPath(this.lock);
        } catch (Exception var4) {
            log.error("Unlock failed. Failed to get data from zookeeper node [{}]", this.lock, var4);
            return;
        }

        if (Arrays.equals(this.data, bytes)) {
            try {
                this.curatorFramework.delete().forPath(this.lock);
            } catch (Exception var3) {
                log.error("Unlock failed. Failed to delete data from zookeeper node [{}]", this.lock, var3);
            }
        }

    }
}

package com.cdf.mall.rocketmq;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description 分销锁工厂
 * @Author hanyaguang
 * @Date 2022/5/24 16:06
 * @Version 1.0
 */
public class DistributedLockFactory implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DistributedLockFactory.class);
    private static String lockPath;
    private static CuratorFramework curatorFramework;

    public DistributedLockFactory(CuratorFramework curatorFramework, String lockPath) {
        DistributedLockFactory.curatorFramework = curatorFramework;
        DistributedLockFactory.lockPath = lockPath;
    }

    public static DistributedLock create(String lock) {
        return new DistributedLock(curatorFramework, lockPath + "/" + lock);
    }

    public void afterPropertiesSet() throws Exception {
        try {
            if (curatorFramework.checkExists().forPath(lockPath) == null) {
                ((ACLBackgroundPathAndBytesable)curatorFramework.create().withMode(CreateMode.PERSISTENT)).forPath(lockPath, new byte[0]);
            }
        } catch (Exception var2) {
            log.error("create task root node error", var2);
        }

    }
}

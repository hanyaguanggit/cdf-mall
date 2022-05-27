package com.cdf.mall.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description 互斥锁工厂类
 * @Author hanyaguang
 * @Date 2022/5/26 11:27
 * @Version 1.0
 */
public class LockFactory implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(LockFactory.class);
    private static String lockPath;
    private static CuratorFramework curatorFramework;

    public LockFactory(CuratorFramework curatorFramework, String applicationName) {
        LockFactory.curatorFramework = curatorFramework;
        lockPath = "/" + applicationName;
    }

    public static InterProcessMutex create(String type, String key) {
        String path = lockPath + "/" + type + "#" + key;
        return new InterProcessMutex(curatorFramework, path);
    }

    @Override
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

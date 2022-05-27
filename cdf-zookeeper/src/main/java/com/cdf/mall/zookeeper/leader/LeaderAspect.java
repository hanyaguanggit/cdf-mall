package com.cdf.mall.zookeeper.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Description zookeep领导者切面
 * @Author hanyaguang
 * @Date 2022/5/26 10:56
 * @Version 1.0
 */
@Aspect
public class LeaderAspect extends LeaderSelectorListenerAdapter implements Closeable, InitializingBean, EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(LeaderAspect.class);
    private Environment environment;
    private CuratorFramework curatorFramework;
    private LeaderSelector leaderSelector;
    private static final long TENURE = 9223372036854775807L;//long的最大值
    private volatile boolean isLeader;

    @Autowired
    public LeaderAspect(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @Override
    public void close() throws IOException {
        this.revokeLeadership();
    }

    /**
     * 撤销
     */
    private void revokeLeadership() {
        this.isLeader = false;
        log.info("Leader revoked");
    }

    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        this.isLeader = true;
        log.info("Leader granted");
        try {
            Thread.sleep(9223372036854775807L);
        } catch (InterruptedException var3) {
        }
        this.revokeLeadership();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String applicationName = this.environment.getProperty("spring.application.name");
        String leaderPath = "/leaderElection/" + applicationName;
        this.leaderSelector = new LeaderSelector(this.curatorFramework, leaderPath, this);
        this.leaderSelector.autoRequeue();
        this.leaderSelector.start();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Around("@annotation(com.cdf.mall.zookeeper.leader.Leader)")
    public void around(ProceedingJoinPoint joinPoint) {
        if (!this.isLeader) {
            log.debug("I am not leader");
        } else {
            try {
                log.debug("I am leader");
                joinPoint.proceed();
            } catch (Throwable var3) {
                log.error(var3.getMessage());
            }

        }
    }
}

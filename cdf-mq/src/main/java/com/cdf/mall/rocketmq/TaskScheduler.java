package com.cdf.mall.rocketmq;

import com.cdf.mall.util.GUID;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 任务调度器
 * @Author hanyaguang
 * @Date 2022/5/24 15:50
 * @Version 1.0
 */
public class TaskScheduler {
    private static final Logger log = LoggerFactory.getLogger(TaskScheduler.class);
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private TaskFacade taskFacade;
    // 初始化运行环境拥有可用处理器的Java虚拟机的数量个线程的线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public TaskScheduler() {
    }

    @PreDestroy
    public void whenDestroy() {
        log.info("释放curatorFramework zookeeper链接");
        this.curatorFramework.close();
        log.info("==============释放curatorFramework zookeeper链接==================");
    }

    public void schedule(OnceTask task) {
        this.schedule(task, true);
    }

    public void schedule(OnceTask task, boolean immediately) {
        if (StringUtils.isEmpty(task.getId())) {
            task.setId(GUID.get());
        }

        if (StringUtils.isEmpty(task.getStatus())) {
            task.setStatus(TaskStatus.INIT.name());
        }

        if (task.getRound() == null) {
            task.setRound(0);
        }

        if (task.getInterval() == null || task.getInterval() == 0) {
            task.setInterval(10000);
        }

        if (task.getRetries() == null || task.getRetries() == 0) {
            task.setRetries(3);
        }

        if (task.getFirstTriggerTime() == null) {
            task.setFirstTriggerTime(new Date());
        }

        if (task.getNextTriggerTime() == null) {
            task.setNextTriggerTime(new Date());
        }

        this.taskDao.insert(task);
        if (immediately) {
            this.executorService.submit(() -> {
                try {
                    this.taskFacade.schedule(task);
                } catch (Exception var3) {
                    var3.printStackTrace();
                    throw var3;
                }
            });
        }

    }

    public boolean exist(Task task) {
        Integer count = this.taskDao.findCountSql(task.getId());
        return count == 1;
    }
}

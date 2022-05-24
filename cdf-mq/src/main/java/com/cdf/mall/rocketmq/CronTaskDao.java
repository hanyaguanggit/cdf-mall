package com.cdf.mall.rocketmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @Description 定时任务定时执行的sql操作
 *
 * @Author hanyaguang
 * @Date 2022/5/24 16:17
 * @Version 1.0
 */
public class CronTaskDao {
    private static final Logger log = LoggerFactory.getLogger(CronTaskDao.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String ERROR_SQL = "update sys_cron_task set error=? where id=?";
    private static final String SUCCESS_SQL = "update sys_cron_task set error='', nextTriggerTime = ? where id=?";

    public CronTaskDao() {
    }

    public void error(Task task, String error) {
        this.jdbcTemplate.update("update sys_cron_task set error=? where id=?", new Object[]{error, task.getId()});
    }

    public void success(Task task, Date nextTriggerTime) {
        this.jdbcTemplate.update("update sys_cron_task set error='', nextTriggerTime = ? where id=?", new Object[]{nextTriggerTime, task.getId()});
    }
}

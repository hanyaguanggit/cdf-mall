package com.cdf.mall.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/24 15:50
 * @Version 1.0
 */
public class TaskDao {
    @Autowired
    @Qualifier("jdbcTemplateTask")
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_SQL = "insert into sys_task(`id`, `name`, `status`, `round`, `retries`, `interval`, `firstTriggerTime`, `nextTriggerTime`, `data`, `error`, `type`, `deleteTime`, `index1`, `index2`, `index3`, `index4`, `index5`, `index6`, `index7`, `index8`, `index9`, `index10`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
    private static final String DELETE_SQL = "delete from sys_task where id=?";
    private static final String UPDATE_SQL = "update sys_task set status = ? where id=?";
    private static final String ERROR_SQL = "update sys_task set error=? where id=?";

    public TaskDao() {
    }

    public void insert(OnceTask task) {
        this.jdbcTemplate.update("insert into sys_task(`id`, `name`, `status`, `round`, `retries`, `interval`, `firstTriggerTime`, `nextTriggerTime`, `data`, `error`, `type`, `deleteTime`, `index1`, `index2`, `index3`, `index4`, `index5`, `index6`, `index7`, `index8`, `index9`, `index10`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)", new Object[]{task.getId(), task.getName(), task.getStatus(), task.getRound(), task.getRetries(), task.getInterval(), task.getFirstTriggerTime(), task.getNextTriggerTime(), task.getData(), task.getError(), task.getType(), task.getDeleteTime(), task.getIndex1(), task.getIndex2(), task.getIndex3(), task.getIndex4(), task.getIndex5(), task.getIndex6(), task.getIndex7(), task.getIndex8(), task.getIndex9(), task.getIndex10()});
    }

    public void update(Task task) {
        this.jdbcTemplate.update("update sys_task set status = ? where id=?", new Object[]{TaskStatus.FINISH.name(), task.getId()});
    }

    public void delete(Task task) {
        this.jdbcTemplate.update("delete from sys_task where id=?", new Object[]{task.getId()});
    }

    public void error(Task task, String error) {
        this.jdbcTemplate.update("update sys_task set error=? where id=?", new Object[]{error, task.getId()});
    }

    public Integer findCountSql(String id) {
        int count = (Integer)this.jdbcTemplate.queryForObject("select count(*) from sys_task where id='" + id + "'", Integer.class);
        return count;
    }
}

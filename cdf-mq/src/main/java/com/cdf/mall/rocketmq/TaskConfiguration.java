package com.cdf.mall.rocketmq;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Description 任务配置类
 * @Author hanyaguang
 * @Date 2022/5/24 15:25
 * @Version 1.0
 */
@Configuration
public class TaskConfiguration {
    @Autowired
    private Environment environment;
    @Value("${task.lockPath:/task}")
    private String lockPath;
    @Value("${spring.application.name}")
    private String application;
    @Value("${spring.cloud.consul.discovery.instance-id}")
    private String instanceId;

    public TaskConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 30000);
        String zooKeeperServers = null;
        if (this.environment.getActiveProfiles()[0].equals("dev")) {
            zooKeeperServers = "10.197.18.6:2181";
        } else if (this.environment.getActiveProfiles()[0].equals("test")) {
            zooKeeperServers = "10.197.2.6:2181";
        } else if (this.environment.getActiveProfiles()[0].equals("prod")) {
            zooKeeperServers = "10.197.1.10:2181,10.197.1.7:2181,10.197.1.14:2181";
        }

        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zooKeeperServers, retryPolicy);
        curatorFramework.start();
        return curatorFramework;
    }

    @Bean
    public DistributedLockFactory distributedLockFactory(CuratorFramework curatorFramework) {
        return new DistributedLockFactory(curatorFramework, this.lockPath);
    }

    @Primary
    @Bean(
            name = {"ConnectionFactoryUser"}
    )
    public ConnectionFactory defaultConnectionFactoryUser() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        if (this.environment.getActiveProfiles()[0].equals("dev")) {
            connectionFactory.setHost("10.197.18.6");
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("UVqkFhZzSUhmJaJWbN");
            connectionFactory.setPort(5672);
        } else if (this.environment.getActiveProfiles()[0].equals("test")) {
            connectionFactory.setHost("10.197.2.12");
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("UVqkFhZzSUhmJaJWbN");
            connectionFactory.setPort(5672);
        } else if (this.environment.getActiveProfiles()[0].equals("prod")) {
            connectionFactory.setHost("amqp-bzg5ampnd9dr.rabbitmq.ap-gz.qcloud.tencenttdmq.com");
            connectionFactory.setUsername("rabbitmq");
            connectionFactory.setPassword("eyJrZXlJZCI6ImFtcXAtYnpnNWFtcG5kOWRyIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJhbXFwLWJ6ZzVhbXBuZDlkcl9yYWJiaXRtcSJ9.qnavmmxwd2FPY9kQtuhMBsUvrzKmUkWBmMK4RlXKDC4");
            connectionFactory.setVirtualHost("amqp-bzg5ampnd9dr|task");
            connectionFactory.setPort(5075);
        }

        return connectionFactory;
    }

    @Bean(
            name = {"rabbitAdminUser"}
    )
    public RabbitAdmin rabbitAdminUser(@Qualifier("ConnectionFactoryUser") ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public TaskDao taskDao() {
        return new TaskDao();
    }

    @Bean
    public CronTaskDao cronTaskDao() {
        return new CronTaskDao();
    }

    @Bean({"apiTaskScheduler"})
    public TaskScheduler taskScheduler() {
        return new TaskScheduler();
    }

    @Bean
    @ConditionalOnMissingBean
    public MeterRegistryCustomizer<?> meterRegistryCustomizer() {
        return (meterRegistry) -> {
            meterRegistry.config().commonTags(new String[]{"application", this.application, "instanceId", this.instanceId});
        };
    }

    @Bean
    public TaskMetrics taskMetrics(MeterRegistry registry) {
        return new TaskMetrics(registry);
    }

    @Bean
    public DataSource dataSourceTask() {
        DruidDataSource druidDataSource;
        if (this.environment.getActiveProfiles()[0].equals("dev")) {
            druidDataSource = DruidDataSourceBuilder.create().build();
            druidDataSource.setUrl("jdbc:mysql://101.33.236.34:3306/middle_system?characterEncoding=utf8&useSSL=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai");
            druidDataSource.setUsername("root");
            druidDataSource.setPassword("_UVqkFhZzSUhmJaJWbN1234");
            druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            return druidDataSource;
        } else if (this.environment.getActiveProfiles()[0].equals("test")) {
            druidDataSource = DruidDataSourceBuilder.create().build();
            druidDataSource.setUrl("jdbc:mysql://106.55.7.180:35002/haikouds_test?characterEncoding=utf8&useSSL=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai");
            druidDataSource.setUsername("bg_test");
            druidDataSource.setPassword("bg123");
            druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            return druidDataSource;
        } else if (this.environment.getActiveProfiles()[0].equals("prod")) {
            druidDataSource = DruidDataSourceBuilder.create().build();
            druidDataSource.setUrl("jdbc:mysql://10.197.0.17:3306/haikouds?characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai");
            druidDataSource.setUsername("han");
            druidDataSource.setPassword("_AAA123123aaa");
            druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            return druidDataSource;
        } else {
            return null;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplateTask(@Qualifier("dataSourceTask") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

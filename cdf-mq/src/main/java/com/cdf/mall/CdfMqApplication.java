package com.cdf.mall;

import org.apache.rocketmq.client.log.ClientLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CdfMqApplication {
    public static void main(String[] args) {
      //  System.setProperty("rocketmq.client.logLevel","ERROR");
        System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J,"true");
        SpringApplication.run(CdfMqApplication.class, args);
    }

}

package com.cdf.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CdfConsulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdfConsulServerApplication.class, args);
    }

}

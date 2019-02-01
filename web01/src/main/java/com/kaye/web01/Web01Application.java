package com.kaye.web01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class Web01Application {

    public static void main(String[] args) {
        SpringApplication.run(Web01Application.class, args);
    }

}


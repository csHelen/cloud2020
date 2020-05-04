package com.atguigu.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: cloud2020
 * @ClassName SentinelMain8402
 * @description:
 * @author: 许
 * @create: 2020-03-31 21:50
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMain8402 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8402.class,args);
    }
}

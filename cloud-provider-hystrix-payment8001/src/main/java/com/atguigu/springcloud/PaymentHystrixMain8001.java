package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @program: cloud2020
 * @ClassName PaymentHystrixMain8001
 * @description:
 * @author: 许
 * @create: 2020-03-25 09:14
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker   //回路
public class PaymentHystrixMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }


}

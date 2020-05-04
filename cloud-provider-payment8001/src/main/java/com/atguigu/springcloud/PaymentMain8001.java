package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: cloud2020
 * @ClassName PaymentMain8001
 * @description:
 * @author: 许
 * @create: 2020-03-17 12:16
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient  //有自我保护机制，为了高可用，服务不可用不会立刻清除，不使用Eureka则直接注释
@EnableDiscoveryClient  //需要服务发现都要有的注释
public class  PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}

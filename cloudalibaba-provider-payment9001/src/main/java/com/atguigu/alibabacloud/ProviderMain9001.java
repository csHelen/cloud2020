package com.atguigu.alibabacloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: cloud2020
 * @ClassName NacosProviderMain9001
 * @description:
 * @author: 许
 * @create: 2020-03-30 17:26
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMain9001.class,args);
    }

}

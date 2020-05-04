package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: cloud2020
 * @ClassName ApplicationContextConfig
 * @description:
 * @author: 许
 * @create: 2020-03-17 15:40
 * @Version 1.0
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced   //赋予restTemplate负载均衡的能力,这样使用微服务名称可以实现负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

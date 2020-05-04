package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @ClassName FeignConfig
 * @description:
 * @author: 许
 * @create: 2020-03-24 11:22
 * @Version 1.0
 **/
@Configuration
public class FeignConfig {

    /**
     * 日志级别
     * NONE     默认不显示任何日志
     * BASIC    仅记录请求方法、URL、相应状态码及执行事件
     * HEADERS  除BASIC中定义的信息之外，还有请求和响应的头信息
     * FULL     除HEADERS中定义的信息之外，还有请求和响应的正文及元数据
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}

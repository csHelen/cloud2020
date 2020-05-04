package com.atguigu.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @ClassName MyBatisConfig
 * @description:
 * @author: 许
 * @create: 2020-04-02 23:54
 * @Version 1.0
 **/
@Configuration
@MapperScan({"com.atguigu.springcloud.alibaba.dao"})
public class MyBatisConfig {
}

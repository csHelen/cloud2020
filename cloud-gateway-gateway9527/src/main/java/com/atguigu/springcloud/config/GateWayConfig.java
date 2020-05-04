package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @ClassName GateWayConfig
 * @description:
 * @author: 许
 * @create: 2020-03-27 17:11
 * @Version 1.0
 **/
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customerRoutes(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu",r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

        return routes.build();

    }

}

package com.atguigu.alibabacloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud2020
 * @ClassName PaymentController
 * @description:
 * @author: 许
 * @create: 2020-03-30 17:29
 * @Version 1.0
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
        return "nacos ...............port:"+serverPort+"\t id:"+id;
    }
}

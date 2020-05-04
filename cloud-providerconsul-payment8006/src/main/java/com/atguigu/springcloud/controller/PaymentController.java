package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: cloud2020
 * @ClassName PaymentController
 * @description:
 * @author: 许
 * @create: 2020-03-21 13:36
 * @Version 1.0
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsol(){
        return "springcloud with consul :"+serverPort+"\t"+ UUID.randomUUID().toString();
    }




}

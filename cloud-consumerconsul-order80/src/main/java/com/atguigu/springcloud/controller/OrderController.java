package com.atguigu.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @ClassName OtherController
 * @description:
 * @author: 许
 * @create: 2020-03-17 15:14
 * @Version 1.0
 **/
@RestController
@Slf4j
public class OrderController {

    public static final String INVOKE_URL = "http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){

        ResponseEntity<String> forEntity = restTemplate.getForEntity(INVOKE_URL + "/payment/consul", String.class);
        return forEntity.getBody();
    }


}

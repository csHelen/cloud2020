package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @ClassName OrderZkController
 * @description:
 * @author: 许
 * @create: 2020-03-20 13:45
 * @Version 1.0
 **/
@RestController
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(INVOKE_URL + "/payment/zk", String.class);
        return forEntity.getBody();
    }

}

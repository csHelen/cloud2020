package com.atguigu.alibabacloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

    public static HashMap<Long, Payment> hashMap= new HashMap<>();
    static{
        hashMap.put(1L,new Payment(1L,"111111"));
        hashMap.put(2L,new Payment(2L,"222222"));
        hashMap.put(3L,new Payment(3L,"333333"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        return new CommonResult(200,"from mysql,serverPort:"+serverPort,payment);
    }
}

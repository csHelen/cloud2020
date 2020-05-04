package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @program: cloud2020
 * @ClassName OrderFeignController
 * @description:
 * @author: 许
 * @create: 2020-03-24 09:37
 * @Version 1.0
 **/
@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeginService paymentFeginService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeginService.getPayment(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openFegin底层ribbon，但客户端默认只等1S
        return paymentFeginService.paymentFeignTimeout();
    }

}

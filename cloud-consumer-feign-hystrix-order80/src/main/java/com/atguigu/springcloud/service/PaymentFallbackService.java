package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @program: cloud2020
 * @ClassName PaymentFallbackService
 * @description:
 * @author: 许
 * @create: 2020-03-27 08:45
 * @Version 1.0
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "--------------------PaymentFallbackService---paymentInfo_ok  fall back";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "--------------------PaymentFallbackService  fall back,--paymentInfo_timeout";
    }
}

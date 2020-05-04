package com.atguigu.alibabacloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @program: cloud2020
 * @ClassName PaymentFallBackService
 * @description:
 * @author: 许
 * @create: 2020-04-02 14:38
 * @Version 1.0
 **/
@Component
public class PaymentFallBackService implements PaymentService{
    @Override
    public CommonResult getPayment(Long id) {
        return new CommonResult(444,"自定义类-PaymentFallBackService-的降级返回",null);
    }
}

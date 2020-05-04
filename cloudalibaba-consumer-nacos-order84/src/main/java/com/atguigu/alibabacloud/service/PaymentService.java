package com.atguigu.alibabacloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallBackService.class)
public interface PaymentService {

    @GetMapping("/paymentSQL/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id);
}

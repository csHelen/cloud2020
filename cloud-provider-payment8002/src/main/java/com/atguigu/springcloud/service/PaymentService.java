package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @program: cloud2020
 * @ClassName PaymentService
 * @description:
 * @author: 许
 * @create: 2020-03-17 14:18
 * @Version 1.0
 **/
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}


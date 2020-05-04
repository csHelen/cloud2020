package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: cloud2020
 * @ClassName PaymentDao
 * @description:
 * @author: 许
 * @create: 2020-03-17 14:06
 * @Version 1.0
 **/
@Mapper     //用repository插入可能有问题
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}

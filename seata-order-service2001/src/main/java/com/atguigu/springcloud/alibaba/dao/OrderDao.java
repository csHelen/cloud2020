package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: cloud2020
 * @ClassName OrderDao
 * @description:
 * @author: 许
 * @create: 2020-04-02 22:39
 * @Version 1.0
 **/
@Mapper
public interface OrderDao {
    void create(Order order);

    void update(@Param("userId") Long userId,@Param("status") Integer status);
}

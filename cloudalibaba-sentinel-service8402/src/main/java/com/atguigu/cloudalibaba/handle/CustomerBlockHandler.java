package com.atguigu.cloudalibaba.handle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

/**
 * @program: cloud2020
 * @ClassName CustomerBlockHandler
 * @description:
 * @author: 许
 * @create: 2020-04-02 11:14
 * @Version 1.0
 **/
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(4444,"按客户自定义,global全局handleException方法---1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(4444,"按客户自定义,global全局handleException方法---2");
    }

}

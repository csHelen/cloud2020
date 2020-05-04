package com.atguigu.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2020
 * @ClassName FlowLimitController
 * @description:
 * @author: 许
 * @create: 2020-03-31 21:53
 * @Version 1.0
 **/
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
//
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "----testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "----testB";
    }

    @GetMapping("/testD")
    public String testD() {
        int age = 10/0;
        return "----testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "------------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "热点访问失败，请稍后重试!";
    }
}

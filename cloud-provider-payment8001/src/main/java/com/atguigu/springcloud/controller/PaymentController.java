package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.mysql.cj.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2020
 * @ClassName PaymentController
 * @description:
 * @author: 许
 * @create: 2020-03-17 14:21
 * @Version 1.0
 **/
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入结果是:"+result);
        if (result>0) {
            return new CommonResult(200,"插入数据库成功，serverPort"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败，serverPort"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******插入结果是:"+payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功，serverPort"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID为"+id+",serverPort"+serverPort,null);

        }

    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> service = discoveryClient.getServices();
        for (String temp : service) {
            log.info("*********element:"+temp);
        }

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance temp : serviceInstances) {
            log.info(temp.getServiceId()+"\t"+temp.getHost()+"\t"+temp.getPort()+"\t"+temp.getUri());
        }

        return this.discoveryClient;

    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "paymentZipkin---!!!!";
    }

}

package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2020
 * @ClassName PaymentService
 * @description:
 * @author: 许
 * @create: 2020-03-25 09:15
 * @Version 1.0
 **/
/**
 * 服务降级可能情况
 *  程序运行异常
 *  超时
 *  服务熔断触发服务降级
 *  线程池/信号量打满也会导致服务降级
 */
@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_ok,id: "+id+"\t";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandle",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_timeout(Integer id){
        int num = 3;
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int age = 10 / 0;
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_timeout,id: "+id+"\t耗时"+num+"s (*^▽^*)";
    }

    public String paymentInfo_timeoutHandle(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" 系统繁忙或者运行报错，请稍后再试,id: "+id +"o(╥﹏╥)o";
    }

    /**
     * 熔断类型：
     *      熔断打开：请求不在进行调用当前服务，内部设置时钟一般为MTTR（平均故障处理时间），当打开时长达到所设时钟则进入半熔断状态
     *      熔断关闭：熔断关闭不会对服务进行熔断
     *      熔断半开：部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，关闭熔断
     *
     *  涉及断路器的三个重要参数：
     *      快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，统计的时间范围就是快照时间窗，默认为最近10秒
     *      请求总数阀值：在快照时间窗被，必须满足请求总数阀值才有资格熔断。默认为20
     *          意味着10秒内，如果调用次数不足20次，就算所有请求都失败断路器也不会打开
     *      错误百分比阀值：默认10秒20次请求的条件下，比如调用了30次，有15次异常，超过50%错误百分比（默认是50%）则断路器打开
     *
     *  断路器打开后，将不会调用主逻辑，直接调用降级fallback，通过断路器实现了自动发现错误并将错误逻辑切换为主逻辑，减少响应延迟时间
     *  主逻辑如何恢复？
     *      hystrix实现了自动恢复功能
     *      断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑
     *      当休眠时间窗到期后，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果正常返回免责断路器闭合，主逻辑恢复
     *      但请求如果还是有问题，则继续保持断路器打开状态，休眠时间窗重新计时
     */

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //请求次数：默认20次
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //时间窗口期：默认10S
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60" ),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("*****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号为:"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试";
    }

}
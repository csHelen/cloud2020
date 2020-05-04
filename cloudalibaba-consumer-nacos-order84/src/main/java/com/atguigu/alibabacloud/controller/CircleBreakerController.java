package com.atguigu.alibabacloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.alibabacloud.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @ClassName OrderNacosController
 * @description:
 * @author: 许
 * @create: 2020-03-30 18:01
 * @Version 1.0
 **/
@RestController
public class CircleBreakerController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    public String serverURL;


    @GetMapping(value = "/consumer/fallback/{id}")

    //sentinel无配置
    //结果故意报错直接报错页面，无友好提示
//    @SentinelResource(value = "fallback")

    //sentinel无配置
    //配置了fallback，只负责业务异常
    //结果：故意报错直接有兜底方法，自定义了友好提示
    //-------------------------------------------
    //sentinel配置了
    //配置了fallback，说明也不知负责业务异常！！！！！！！
    //结果：sentinel限流也走fallback方法，但是异常显示为null
//    @SentinelResource(value = "fallback",fallback = "handleFallback")


    //sentinel无配置
    //配置了blockHandler，值负责sentinel控制台配置违规
    //结果：故意出错直接报错误页面，blockHandle配置无效~~~~
    //----------------------------------------------------
    //sentinel配置降级配置：异常数2个
    //配置了blockHandler，值负责sentinel控制台配置违规
    //结果：两次报错页面之后，blockHandle生效，sentinel异常将由自定义的方法进行友好提示
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")


    //sentinel,fallback,blockHandle均配置
    // 正常则运行，系统报错则自定义fallback处理，sentinel限流则blockHandler处理
    // 大部分都是blockHandler来处理
    @SentinelResource(value = "fallback",fallback = "handleFallback",blockHandler = "blockHandler")


    //新添一个忽略异常的属性
    //exceptionToIgnore
    //结果：非法参数异常直接报错误页面，无友好提示，且没有计算进sentinel的异常处理
//    @SentinelResource(value = "fallback",
//            fallback = "handleFallback",
//            blockHandler = "blockHandler",
//            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult forObject = restTemplate.getForObject(serverURL + "/paymentSQL/" + id, CommonResult.class, id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常....");
        }else if(forObject.getData() == null){
            throw new NullPointerException("NullPointerException，该ID没有对应记录...");
        }
        return forObject;
    }
    public CommonResult<Payment> handleFallback(@PathVariable("id") Long id,Throwable e){
        return new CommonResult(444,"兜底异常"+e.getMessage(),null);
    }

    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id, BlockException e){
        return new CommonResult(445,"BlockException-sentinel限流，无此流水,,"+e.getMessage(),null);
    }

    @Resource
    public PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    //正常访问没问题
    //关闭9003.9004就会有友好报错信息，但是如果访问其ID=4和ID=5的故意抛出异常资源，不会抛出异常，只会返回为空，访问没问题
    public CommonResult getPayment(@PathVariable("id") Long id){
        return paymentService.getPayment(id);
    }

}

package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

/**
 * @program: cloud2020
 * @ClassName IMessageProviderImpl
 * @description:
 * @author: 许
 * @create: 2020-03-28 23:41
 * @Version 1.0
 **/

@EnableBinding(Source.class)    //定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    //消息发送通道
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("**************serial: "+serial);

        return null;
    }
}

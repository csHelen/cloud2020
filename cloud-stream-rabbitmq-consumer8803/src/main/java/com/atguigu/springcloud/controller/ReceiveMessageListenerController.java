package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloud2020
 * @ClassName ReceiveMessageListenerController
 * @description:
 * @author: 许
 * @create: 2020-03-29 10:05
 * @Version 1.0
 **/
@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){

        System.out.println("消费者2号，-------->收到的消息："+message.getPayload()+"\t port:"+serverPort);
    }

}

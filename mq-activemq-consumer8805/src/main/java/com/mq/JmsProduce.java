package com.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @program: cloud2020
 * @ClassName JmsProduce
 * @description:
 * @author: 许
 * @create: 2020-05-01 18:17
 * @Version 1.0
 **/
public class JmsProduce {

    public static final String AACTIVEMQ_URL = "nio://127.0.0.1:61618";
    public static final String QUEUE_NAME = "queue_nio_01";
    public static void main(String[] args) throws JMSException {
        //连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(AACTIVEMQ_URL);
        //连接connection并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        for (int i = 1; i <= 6 ; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            //发送信息
            producer.send(textMessage);
        }
        //关闭
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送完毕");
    }

}

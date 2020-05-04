package com.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @program: cloud2020
 * @ClassName JmsProduce_Topic
 * @description:
 * @author: 许
 * @create: 2020-05-01 21:42
 * @Version 1.0
 **/
public class JmsProduce_Topic {


    public static final String AACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic_default";
    public static void main (String[]args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(AACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        for (int i = 1; i <= 6 ; i++) {
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            producer.send(textMessage);
        }
        session.close();
        connection.close();
        System.out.println("消息发送完毕");
    }



}

package com.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @program: cloud2020
 * @ClassName MyConsumer
 * @description:
 * @author: 许
 * @create: 2020-04-07 21:34
 * @Version 1.0
 **/

public class JmsConsumer_Topic {

    public static final String AACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic_default";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("1号");
        asyncroReceive();
    }

    //异步
    public static void asyncroReceive() throws JMSException, IOException {
        //连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(AACTIVEMQ_URL);
        //连接connection并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建消息的生产者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        //消息监听接受消息
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(Thread.currentThread().getName() + "接收到的消息:" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}

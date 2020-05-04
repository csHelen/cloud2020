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

public class JmsConsumer_Topic_Persist {

    public static final String AACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic_persist";
    public static void main(String[] args) throws JMSException, IOException {
        asyncroReceive();
    }
    //异步
    public static void asyncroReceive() throws JMSException, IOException {
        System.out.println("z3");
        //连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(AACTIVEMQ_URL);
        //连接connection并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("z3");
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地，具体是队列还是主题topic
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"remark....");
        //启动
        connection.start();

        Message message = topicSubscriber.receive();
        while(null != message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("受到的持久化信息为:"+textMessage.getText());
            message = topicSubscriber.receive();
        }
        session.close();
        connection.close();
    }
}

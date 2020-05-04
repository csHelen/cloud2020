package com.nio;

import org.apache.activemq.broker.BrokerService;

/**
 * @program: cloud2020
 * @ClassName EmbedBroker
 * @description:
 * @author: 许
 * @create: 2020-05-02 10:35
 * @Version 1.0
 **/
public class EmbedBroker {


    public static void main(String[] args) throws Exception {

        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61615");
        brokerService.start();

    }

}

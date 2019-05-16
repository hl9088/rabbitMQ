package com.rabbitmq.demo5.topic;

import com.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Created by lihongli on 2019/5/15
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "demo5_topic");

        // 消息
        String message1 = "Hello world";
        channel.basicPublish(EXCHANGE_NAME, "usa.news", null, message1.getBytes());
        String message2 = "Today is a nice day";
        channel.basicPublish(EXCHANGE_NAME, "usa.weather", null, message2.getBytes());

        System.out.println(" [x] send " + message1);
        System.out.println(" [x] send " + message2);

        channel.close();
        connection.close();
    }
}



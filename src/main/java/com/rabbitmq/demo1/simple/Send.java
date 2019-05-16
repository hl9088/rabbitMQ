package com.rabbitmq.demo1.simple;

import com.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Created by lihongli on 2019/5/15
 */
public class Send {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] send " + message + "");

        // 关闭通道和连接
        channel.close();
        connection.close();
    }

}

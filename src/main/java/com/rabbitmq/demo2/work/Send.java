package com.rabbitmq.demo2.work;

import com.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 一个生产者、2个消费者。一个消息只能被一个消费者获取。
 *
 * Created by lihongli on 2019/5/15
 */
public class Send {

    private final static String QUEUE_NAME = "q_test_work";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发送100条信息
        for (int i = 0; i < 1000; i++) {
            // 消息内容
            String message = "Hello World" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] send " + message + "");
        }

        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}

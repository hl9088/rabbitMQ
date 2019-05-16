package com.rabbitmq.demo4.routing;

import com.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Created by lihongli on 2019/5/15
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息
        String message1 = "删除商品";
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message1.getBytes());
        String message2 = "修改商品";
        channel.basicPublish(EXCHANGE_NAME, "update", null, message2.getBytes());
        String message3 = "新增商品";
        channel.basicPublish(EXCHANGE_NAME, "insert", null, message3.getBytes());
        System.out.println(" [x] send " + message1);
        System.out.println(" [x] send " + message2);
        System.out.println(" [x] send " + message3);

        channel.close();
        connection.close();
    }
}



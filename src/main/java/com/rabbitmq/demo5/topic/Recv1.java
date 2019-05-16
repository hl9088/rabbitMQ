package com.rabbitmq.demo5.topic;

import com.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 路由模式
 * 根据消息发送时 绑定的routekey 判断消息有哪个路由接收
 * Created by lihongli on 2019/5/15
 */
public class Recv1 {

    private static final String QUEUE_NAME = "test_queue_topic1";

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "usa.*");

        // 同一时刻服务器只发一条信息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列 手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (2 > 1) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);

            Thread.sleep(5);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }
}

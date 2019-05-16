package com.rabbitmq.demo2.work;

import com.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 一个生产者、2个消费者。一个消息只能被一个消费者获取。
 * <p>
 * Created by lihongli on 2019/5/15
 */
public class Recv2 {

    private final static String QUEUE_NAME = "q_test_work";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只发一条信息给消费者
        channel.basicQos(1);// 根据处理能力分配任务 不采用轮训方式 这行代码不能注释掉

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列 false表示手动返回完成状态 true表示自动
        channel.basicConsume(QUEUE_NAME, false, consumer);//根据处理能力分配任务 不采用轮训方式 采用手动返回

        // 获取消息
        while (2 > 1) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] received " + message);

            // 休眠1000ms
            Thread.sleep(1000);

            // 返回确认状态 注释掉表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);// 根据处理能力分配任务 不采用轮训方式 这行代码不能注释掉
        }
    }
}

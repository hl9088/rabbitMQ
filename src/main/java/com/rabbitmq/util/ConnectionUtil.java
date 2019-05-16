package com.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by lihongli on 2019/5/15
 */
public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("localhost");
        // 端口
        factory.setPort(5672);
        // 设置账号信息 用户名 密码 虚拟主机
        factory.setVirtualHost("test");
        factory.setUsername("admin");
        factory.setPassword("123456");
        // 通过工厂获取连接
        return factory.newConnection();
    }
}

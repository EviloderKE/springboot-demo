package com.example.consumer.listen;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "hello")
public class Demo1 {

    @RabbitHandler
    public void receive(String in, Channel channel, Message message) throws IOException {

        System.out.println(" [x] Received '" + in + "'");

        // 手动确认收到信息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

        System.out.println("消息已确认");
    }

}

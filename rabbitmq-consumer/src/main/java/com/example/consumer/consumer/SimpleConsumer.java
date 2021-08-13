package com.example.consumer.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "simple")
@Slf4j
public class SimpleConsumer {

    @RabbitHandler
    public void receive(String in, Channel channel, Message message) throws IOException {

        log.info("接受到消息:{}", in);

        // 手动确认收到信息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}

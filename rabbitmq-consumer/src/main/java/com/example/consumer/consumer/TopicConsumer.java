package com.example.consumer.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TopicConsumer {

    @RabbitHandler
    @RabbitListener(queues = "topicQueue1")
    public void receive1(String in, Channel channel, Message message) throws IOException {
        log.info("topicQueue1接收到消息:{}", in);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitHandler
    @RabbitListener(queues = "topicQueue2")
    public void receive2(String in, Channel channel, Message message) throws IOException {
        log.info("topicQueue2接收到消息:{}", in);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitHandler
    @RabbitListener(queues = "topicQueue3")
    public void receive3(String in, Channel channel, Message message) throws IOException {
        log.info("topicQueue3接收到消息:{}", in);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}

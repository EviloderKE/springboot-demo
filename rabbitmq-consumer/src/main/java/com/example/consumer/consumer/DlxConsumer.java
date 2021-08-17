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
public class DlxConsumer {

//    @RabbitHandler
//    @RabbitListener(queues = "businessQueue")
//    public void receive1(String in, Channel channel, Message message) throws IOException {
//        log.info("businessQueue接收到消息:{}", in);
//
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//
//        // nack可以批量拒绝消息 reject单个消息
//        //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//    }

    @RabbitHandler
    @RabbitListener(queues = "dlxQueue")
    public void receive2(String in, Channel channel, Message message) throws IOException {
        log.info("dlxQueue接收到消息:{}", in);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}

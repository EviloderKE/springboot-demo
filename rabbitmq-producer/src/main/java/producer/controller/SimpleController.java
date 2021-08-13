package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/simple")
@Slf4j
public class SimpleController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue hello;

    @RequestMapping("/send")
    public void send(){
        // 简单消息传递
        rabbitTemplate.convertAndSend(hello.getName(), "send");

        System.out.println("send success");
    }

    @RequestMapping("/send1")
    public void send2(){
        String message = "send1";

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(hello.getName(), (Object) message, correlationData);

        rabbitTemplate.setConfirmCallback((correlationData1, ack, cause) -> {
            log.info("CorrelationData content : " + correlationData1);
            log.info("Ack status : " + ack);
            log.info("Cause content : " + cause);    // 失败时提示 正常为null
            if(ack){
                log.info("消息成功发送");
            }else{
                log.info("消息发送失败: {}, 出现异常: {}", correlationData1, cause);
            }
        });
    }

    @RequestMapping("/send2")
    public void sendAndReturn() {
        String msg = "send2";
        // 没有routingKey会被返回
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("被退回的消息为：{}", message);
            log.info("replyCode：{}", replyCode);
            log.info("replyText：{}", replyText);
            log.info("exchange：{}", exchange);
            log.info("routingKey：{}", routingKey);
        });

        rabbitTemplate.convertAndSend("fail", msg);
        log.info("消息发送完毕。");
    }


}

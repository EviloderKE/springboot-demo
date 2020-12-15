package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import producer.Publish.AckPublisher;

import java.util.UUID;

@RestController
@RequestMapping("/demo1")
@Slf4j
public class ProducerDemo1Controller {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("hello")
    private Queue queue;

    @Autowired
    @Qualifier("hello1")
    private Queue queue1;

    @Autowired
    private AckPublisher ackPublisher;

    @RequestMapping("/send")
    public void send(){
        rabbitTemplate.convertAndSend(queue.getName(), "send");

        System.out.println("send success");
    }

    @RequestMapping("/send1")
    public void send1(){
        rabbitTemplate.convertAndSend(queue1.getName(), "send1");

        System.out.println("send1 success");
    }

    @RequestMapping("/send2")
    public void send2(){
        String message = "send2";

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(queue.getName(), (Object) message, correlationData);

        rabbitTemplate.setConfirmCallback((correlationData1, ack, cause) -> {
            log.info("CorrelationData content : " + correlationData1);
            log.info("Ack status : " + ack);
            log.info("Cause content : " + cause);
            if(ack){
                log.info("消息成功发送，订单入库，更改订单状态");
            }else{
                log.info("消息发送失败："+ correlationData1 +", 出现异常："+cause);
            }
        });
    }

    @RequestMapping("/send3")
    public void send3(){
        ackPublisher.publish("send3");

        System.out.println("send3 success");
    }

}

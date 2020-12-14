package producer.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1")
public class ProducerDemo1Controller {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("hello")
    private Queue queue;

    @Autowired
    @Qualifier("hello1")
    private Queue queue1;

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

}

package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
@Slf4j
public class TopicController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    @GetMapping("send")
    public void send(){
        rabbitTemplate.convertAndSend(topicExchange.getName(), "a.queue1.b", "a.queue1.b");

        rabbitTemplate.convertAndSend(topicExchange.getName(), "a.b.queue2", "a.b.queue2");

        rabbitTemplate.convertAndSend(topicExchange.getName(), "queue3.a", "queue3.a");

        rabbitTemplate.convertAndSend(topicExchange.getName(), "queue3", "queue3");

        rabbitTemplate.convertAndSend(topicExchange.getName(), "queue3.a.b", "queue3.a.b");
    }

}

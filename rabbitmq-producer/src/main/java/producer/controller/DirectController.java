package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direct")
@Slf4j
public class DirectController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    @GetMapping("send")
    public void send(){
        rabbitTemplate.convertAndSend(directExchange.getName(), "queue1", "direct1");

        rabbitTemplate.convertAndSend(directExchange.getName(), "queue2", "direct2");
    }

}

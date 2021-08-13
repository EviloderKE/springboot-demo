package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fanout")
@Slf4j
public class FanoutController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;

    @GetMapping("send")
    public void send(){
        String message = "fanout";

        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
    }

}

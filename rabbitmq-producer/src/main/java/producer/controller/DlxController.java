package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dlx")
@Slf4j
public class DlxController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange businessExchange;

    @GetMapping("send")
    public void send(){
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(businessExchange.getName(), "", i);
        }
    }

    @GetMapping("send1/{ttl}")
    public void sendTtl(@PathVariable String ttl){

        rabbitTemplate.convertAndSend(businessExchange.getName(),"",ttl, message -> {
            // 超时时间
            message.getMessageProperties().setExpiration(ttl);
            return message;
        });
    }

}

package producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2");
    }

    @Bean
    public Binding bindingFanoutQueue1(FanoutExchange fanoutExchange, Queue fanoutQueue1){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutQueue2(FanoutExchange fanoutExchange, Queue fanoutQueue2){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

}

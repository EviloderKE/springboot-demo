package producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue directQueue1(){
        return new Queue("directQueue1");
    }

    @Bean
    public Queue directQueue2(){
        return new Queue("directQueue2");
    }

    @Bean
    public Binding bindingDirectQueue1(DirectExchange directExchange, Queue directQueue1){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("queue1");
    }

    @Bean
    public Binding bindingDirectQueue2(DirectExchange directExchange, Queue directQueue2){
        return BindingBuilder.bind(directQueue2).to(directExchange).with("queue2");
    }

}

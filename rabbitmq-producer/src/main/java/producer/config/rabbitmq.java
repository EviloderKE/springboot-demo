package producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitmq {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

}

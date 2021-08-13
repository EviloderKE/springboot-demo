package producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Simple {

    @Bean
    public Queue simple() {
        return new Queue("simple");
    }

}

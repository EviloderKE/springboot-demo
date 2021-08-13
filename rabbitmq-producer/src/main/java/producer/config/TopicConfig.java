package producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Queue topicQueue1(){
        return new Queue("topicQueue1");
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue("topicQueue2");
    }

    @Bean
    public Queue topicQueue3(){
        return new Queue("topicQueue3");
    }

    @Bean
    public Binding bindingTopicQueue1(TopicExchange topicExchange, Queue topicQueue1){
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("*.queue1.*");
    }

    @Bean
    public Binding bindingTopicQueue2(TopicExchange topicExchange, Queue topicQueue2){
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("*.*.queue2");
    }

    @Bean
    public Binding bindingTopicQueue3(TopicExchange topicExchange, Queue topicQueue3){
        return BindingBuilder.bind(topicQueue3).to(topicExchange).with("queue3.#");
    }
}

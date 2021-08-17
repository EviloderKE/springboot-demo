package producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadLetterConfig {

    public final String dlxExchange = "dlxExchange";

    public final String dlxQueue = "dlxQueue";

    public final String dlxRouteKey = "dlx";

    public final String businessQueue = "businessQueue";

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public DirectExchange dlxExchange(){
        return new DirectExchange(dlxExchange);
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue dlxQueue(){
        return new Queue(dlxQueue);
    }

    @Bean
    public Binding bindingDlxQueue1(DirectExchange dlxExchange, Queue dlxQueue){
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with(dlxRouteKey);
    }

    /**
     * 业务队列
     * @return
     */
    @Bean()
    public Queue businessQueue(){
        Map<String, Object> hashMap = new HashMap<>();

        // 当前队列绑定的死信交换机
        hashMap.put("x-dead-letter-exchange", dlxExchange);

        // 当前队列的死信路由键
        hashMap.put("x-dead-letter-routing-key", dlxRouteKey);

        // 设置队列消息的超时时间  毫秒
        //hashMap.put("x-message-ttl", 10000);

        // 生命队列的最大长度，超过长度的消息进入死信队列
        //hashMap.put("x-max-length", 2);

        return QueueBuilder.durable(businessQueue).withArguments(hashMap).build();
    }

    @Bean
    public FanoutExchange businessExchange(){
        return new FanoutExchange("businessExchange");
    }

    @Bean
    public Binding bindingBusinessExchange(FanoutExchange businessExchange, Queue businessQueue){
        return BindingBuilder.bind(businessQueue).to(businessExchange);
    }

}

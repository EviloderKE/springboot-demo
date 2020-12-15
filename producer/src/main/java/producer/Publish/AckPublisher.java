package producer.Publish;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class AckPublisher implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("hello")
    private Queue queue;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 接收发送后确认信息
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("ack send succeed: " + correlationData);
        } else {
            System.out.println("ack send failed: " + correlationData + "|" + cause);
        }
    }

    /**
     * 发送失败的回调
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("ack " + message + " 发送失败");
    }

    /**
     * 一般的用法，推送消息
     *
     * @param ans
     * @return
     */
    public String publish(String ans) {
        String message = "ack msg = " + ans;

        System.out.println(message);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(queue.getName(), (Object) message, correlationData);

        return message;
    }
}

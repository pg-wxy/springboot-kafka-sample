package hyron.kafka.demo.SpringBootDemo.producer;

import hyron.kafka.demo.SpringBootDemo.domain.MyObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyProducer {
    @Autowired
    private KafkaTemplate<String, MyObject> kafkaTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void send(String topic, MyObject data) {
        log.info("sending payload='{}' to topic='{}'", data, topic);

        Message<MyObject> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topic).build();

        this.kafkaTemplate.send(message);
    }
}

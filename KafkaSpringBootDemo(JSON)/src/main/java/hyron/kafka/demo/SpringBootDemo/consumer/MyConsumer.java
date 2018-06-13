package hyron.kafka.demo.SpringBootDemo.consumer;

import hyron.kafka.demo.SpringBootDemo.domain.MyObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class MyConsumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "user-topic")
    public void receive(@Payload MyObject payload, @Headers MessageHeaders headers) {
        log.info("received payload='{}'",payload);
        headers.keySet().forEach(key -> {
            log.info("{}: {}", key, headers.get(key));
        });

        latch.countDown();
    }
}

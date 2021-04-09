package com.unique;

import java.util.concurrent.TimeUnit;

import com.unique.kafka.consumer.Receiver;
import com.unique.kafka.producer.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(
        partitions = 2,
        topics = {"batch.topicDemo"},
        controlledShutdown = true,
        brokerProperties = {"log.dir=out/embedded-kafka"})       // simulate a broker
public class KafkaTest {

    @Autowired
    private Receiver receiver;

    @Autowired
    private Sender sender;

    @Test
    public void testReceive() throws Exception {

        int messageCount = Receiver.COUNT;
        for (int i = 0; i < messageCount; i++) {
            sender.send("batch.topicDemo", "message " + i);
        }

        receiver.getLatch().await(100000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}

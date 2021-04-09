package com.unique.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class Receiver {

    public static final int COUNT = 20;

    private CountDownLatch latch = new CountDownLatch(COUNT);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = {"batch.topicDemo"}, containerFactory = "batchListenerContainerFactory")
    public void batchReceiver(List<ConsumerRecord<String, String>> records) {
        log.info("start of batch receive");
        for (int i = 0; i < records.size(); i++) {
            log.info("received Partition-Offset = {}-{} message = {}", records.get(i).partition(), records.get(i).offset(), records.get(i).value());

            latch.countDown();
        }
        log.info("end of batch receive");
    }

}

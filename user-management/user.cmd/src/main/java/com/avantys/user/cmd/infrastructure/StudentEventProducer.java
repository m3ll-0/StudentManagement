package com.avantys.user.cmd.infrastructure;

import com.avantys.cqrs.core.events.BaseEvent;
import com.avantys.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * StudentEventProducer implements the EventProducer interface, which signifies that it is a producer that produces events
 * to the message broker.
 */
@Service
public class StudentEventProducer implements EventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Produce a kafka event.
     */
    @Override
    public void produce(String topicName, BaseEvent event) {
        this.kafkaTemplate.send(topicName, event);
    }
}

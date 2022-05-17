package com.avantys.user.cmd.infrastructure.consumers;

import com.avantys.user.cmd.api.events.AssessStudentEvent;
import com.avantys.user.cmd.api.events.StudentRegisteredEvent;
import com.avantys.user.cmd.infrastructure.handlers.EventHandler;
import com.avantys.user.cmd.api.events.AcceptStudentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * AccountEventConsumer implements the EventConsumer interface.
 * Is responsible for consuming events from the message broker, and then calling the appropriate eventHandler which will
 * in turn save an event to the read database.
 */
@Service
public class StudentEventConsumer implements EventConsumer{
    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "StudentRegisteredEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(StudentRegisteredEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AcceptStudentEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AcceptStudentEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AssessStudentEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AssessStudentEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}

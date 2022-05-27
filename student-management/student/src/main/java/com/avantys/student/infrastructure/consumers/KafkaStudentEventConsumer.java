package com.avantys.student.infrastructure.consumers;

import com.avantys.student.api.events.AssessStudentEvent;
import com.avantys.student.api.events.AuthorizePaymentMethodEvent;
import com.avantys.student.api.events.StudentRegisteredEvent;
import com.avantys.student.infrastructure.handlers.EventHandler;
import com.avantys.student.api.events.AcceptStudentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * StudentEventConsumer implements the EventConsumer interface.
 * Is responsible for consuming events from the message broker, and then calling the appropriate eventHandler which will
 * in turn save an event to the read database.
 */
@Service
public class KafkaStudentEventConsumer implements EventConsumer{
    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "StudentRegisteredEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(StudentRegisteredEvent event) {
        eventHandler.on(event);
    }

    @KafkaListener(topics = "AcceptStudentEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AcceptStudentEvent event) {
        eventHandler.on(event);
    }

    @KafkaListener(topics = "AssessStudentEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AssessStudentEvent event) {
        eventHandler.on(event);
    }

    @KafkaListener(topics = "AuthorizePaymentMethodEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AuthorizePaymentMethodEvent event) {
        eventHandler.on(event);
    }
}

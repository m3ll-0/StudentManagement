package com.avantys.student.infrastructure.consumers;

import com.avantys.student.api.events.AcceptStudentEvent;
import com.avantys.student.api.events.AssessStudentEvent;
import com.avantys.student.api.events.AuthorizePaymentMethodEvent;
import com.avantys.student.api.events.StudentRegisteredEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * EventConsumer interface to abstract all eventConsumers
 * @Payload annotations for event binds a method parameter to the payload of a message
 */
public interface EventConsumer {
    void consume(@Payload StudentRegisteredEvent event);
    void consume(@Payload AcceptStudentEvent event);
    void consume(@Payload AssessStudentEvent event);
    void consume(@Payload AuthorizePaymentMethodEvent event);
}

package com.avantys.student.infrastructure.consumers;

import com.avantys.student.api.events.StudentAcceptedEvent;
import com.avantys.student.api.events.StudentAssessedEvent;
import com.avantys.student.api.events.StudentPaymentMethodAuthorizedEvent;
import com.avantys.student.api.events.StudentRegisteredEvent;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * EventConsumer interface to abstract all eventConsumers
 * @Payload annotations for event binds a method parameter to the payload of a message
 */
public interface EventConsumer {
    void consume(@Payload StudentRegisteredEvent event);
    void consume(@Payload StudentAcceptedEvent event);
    void consume(@Payload StudentAssessedEvent event);
    void consume(@Payload StudentPaymentMethodAuthorizedEvent event);
}

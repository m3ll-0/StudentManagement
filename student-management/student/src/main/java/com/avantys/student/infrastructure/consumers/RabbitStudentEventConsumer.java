package com.avantys.student.infrastructure.consumers;

import com.avantys.student.api.events.StudentAcceptedEvent;
import com.avantys.student.api.events.StudentAssessedEvent;
import com.avantys.student.api.events.StudentPaymentMethodAuthorizedEvent;
import com.avantys.student.api.events.StudentRegisteredEvent;
import com.avantys.student.infrastructure.handlers.EventHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Primary
@RabbitListener(queues = "Avantys")
public class RabbitStudentEventConsumer implements EventConsumer
{
    @Autowired
    private EventHandler eventHandler;

    @RabbitHandler()
    public void consume(@Payload StudentRegisteredEvent event) {
        eventHandler.on(event);
    }

    @RabbitHandler()
    public void consume(@Payload StudentAcceptedEvent event) {
        eventHandler.on(event);
    }

    @RabbitHandler()
    public void consume(@Payload StudentAssessedEvent event) {
        eventHandler.on(event);
    }

    @RabbitHandler()
    public void consume(@Payload StudentPaymentMethodAuthorizedEvent event) {
        eventHandler.on(event);
    }
}

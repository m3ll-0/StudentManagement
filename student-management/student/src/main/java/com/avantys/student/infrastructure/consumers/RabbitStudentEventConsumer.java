package com.avantys.student.infrastructure.consumers;

import com.avantys.student.api.controllers.AcceptStudentController;
import com.avantys.student.api.events.AcceptStudentEvent;
import com.avantys.student.api.events.AssessStudentEvent;
import com.avantys.student.api.events.AuthorizePaymentMethodEvent;
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
    public void consume(@Payload AcceptStudentEvent event) {
        eventHandler.on(event);
    }

    @RabbitHandler()
    public void consume(@Payload AssessStudentEvent event) {
        eventHandler.on(event);
    }

    @RabbitHandler()
    public void consume(@Payload AuthorizePaymentMethodEvent event) {
        eventHandler.on(event);
    }
}

package com.avantys.user.cmd.infrastructure.consumers;

import com.avantys.user.common.events.AcceptStudentEvent;
import com.avantys.user.common.events.RegisterStudentEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * EventConsumer interface to abstract all eventConsumers
 * @Payload annotations for event binds a method parameter to the payload of a message
 */
public interface EventConsumer {
    void consume(@Payload RegisterStudentEvent event, Acknowledgment ack);
    void consume(@Payload AcceptStudentEvent event, Acknowledgment ack);
}

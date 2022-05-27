package com.avantys.student.infrastructure.producers;

import com.avantys.cqrs.core.events.BaseEvent;
import com.avantys.cqrs.core.producers.EventProducer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class RabbitStudentEventProducer implements EventProducer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Override
    public void produce(String topicName, BaseEvent event) {
        this.template.setMessageConverter(new Jackson2JsonMessageConverter());
        this.template.convertAndSend(queue.getName(), event);
    }
}

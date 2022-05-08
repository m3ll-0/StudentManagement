package com.avantys.cqrs.core.producers;

import com.avantys.cqrs.core.events.BaseEvent;

/**
 * Interface for abstracting concrete producers
 */
public interface EventProducer {
    void produce(String topicName, BaseEvent event);
}

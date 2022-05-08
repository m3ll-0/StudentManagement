package com.avantys.cqrs.core.handlers;

import com.avantys.cqrs.core.domain.AggregateRoot;

/**
 * EventSourcingHandler provides an abstraction through which the commandHandler can obtain the latest state of the aggregate
 * It basically sits between the commandHandler and the EventStore
 */
public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);
    T getById(String id);
}

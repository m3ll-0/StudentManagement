package com.avantys.cqrs.core.infrastructure;

import com.avantys.cqrs.core.events.BaseEvent;

import java.util.List;

/**
 * EventStore interface provides an abstraction for accessing the eventStore business logic.
 */
public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);
}

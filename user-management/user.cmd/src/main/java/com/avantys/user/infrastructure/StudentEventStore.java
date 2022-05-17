package com.avantys.user.infrastructure;

import com.avantys.user.domain.StudentAggregate;
import com.avantys.user.domain.EventStoreRepository;
import com.avantys.cqrs.core.events.BaseEvent;
import com.avantys.cqrs.core.events.EventModel;
import com.avantys.cqrs.core.exceptions.AggregateNotFoundException;
import com.avantys.cqrs.core.exceptions.ConcurrencyException;
import com.avantys.cqrs.core.infrastructure.EventStore;
import com.avantys.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsible for versioning the events for a given aggregate and persisting new event versions to the eventstore
 * via the eventRepository.
 * It is also responsible for retrieve all stored events in the event store to replay the events to recreate the latest state of the aggregate.
 */
@Service
public class StudentEventStore implements EventStore {
    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private EventStoreRepository eventStoreRepository;

    /**
     * Saves events to the write-database and produces events to Kafka.
     * Called from the save() method of the StudentEventSourcingHandler, which in turn is being called from a commandHandler.
     */
    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        // Get all events for a given aggregate
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);

        // If it is not -1 (when we create a new aggregate it is -1), it indicates that it is not a new aggregate
        if(expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }

        var version  = expectedVersion;
        for(var event: events){
            version++; // increment the version
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(StudentAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();

            // Save the event to the write-database using the eventStoreRepository
            var persistedEvent = eventStoreRepository.save(eventModel);

            // Produce event to Kafka
            if(!persistedEvent.getId().isEmpty()){
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    /**
     * Returns a list of events from the eventStoreRepository (from the write database) by a given aggregateId.
     * Is called from the getById method in the StudentEventSourcingHandler to recreate the latest state of the aggregate.
     */
    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);

        if(eventStream == null || eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect student ID provided!");
        }

        // Translate the eventStream into to list of baseEvents
        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }
}

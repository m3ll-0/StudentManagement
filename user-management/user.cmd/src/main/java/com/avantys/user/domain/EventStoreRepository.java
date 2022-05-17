package com.avantys.user.domain;

import com.avantys.cqrs.core.events.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EventStoreRepository extends the MongoRepository.
 * It uses the repository pattern.
 * Extending the MongoRepository makes sure that is has access to standard methods like save, findById and deleteById
 * The standard save method is used to save events to the event store
 */
@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
    /**
     * Get all events for a given aggregate
     */
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}

package com.avantys.user.cmd.infrastructure;

import com.avantys.user.cmd.domain.StudentAggregate;
import com.avantys.cqrs.core.domain.AggregateRoot;
import com.avantys.cqrs.core.handlers.EventSourcingHandler;
import com.avantys.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

/**
 * StudentEventSourcingHandler class implements the EventSourcingHandler interface
 */
@Service
public class StudentEventSourcingHandler implements EventSourcingHandler<StudentAggregate> {

    @Autowired
    private EventStore eventStore;

    /**
     * Save the uncommitted changes (events) to the eventStore and mark it as committed (clear the list of changes).
     * Is being called from the handle() method in the StudentCommandHandler class.
     */
    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getId(), aggregateRoot.getUncommitedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();
    }

    /**
     * Recreate the latest state of the aggregate and return it
     */
    @Override
    public StudentAggregate getById(String id) {
        var aggregate = new StudentAggregate();
        // Get events from the eventStore
        var events = eventStore.getEvents(id);

        if(events != null && !events.isEmpty()){
            // Recreate the latest state of the aggregate by applying the events stored in the event store
            // to create the state of the aggregate
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }

        // Return the aggregate which state was recreated by replaying the event store
        return aggregate;
    }
}

package com.avantys.cqrs.core.domain;

import com.avantys.cqrs.core.events.BaseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The AggregateRoot class is an abstract class and is responsible for maintaining the consistent state of the aggregate.
 */
public abstract class AggregateRoot {
    protected String id;
    private int version = -1;

    // Contains all the changes that are made to the aggregate in the form of events
    private final List<BaseEvent> changes = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public String getId(){
        return this.id;
    }

    public int getVersion(){
        return this.version;
    }

    public void setVersion(int version){
        this.version = version;
    }

    /**
     * Return list of changes (in the form of events) made to the aggregate.
     * This is called by the AccountEventSourcingHandler as an argument to pass to the eventStore.
     * The event store receives the changes and publishes them to the write-database.
     */
    public List<BaseEvent> getUncommitedChanges(){
        return this.changes;
    }

    /**
     * Clear the list of changes so that when new events are added, they are new changes.
     * This is called after the event has been saved in the eventStore in the AccountEventSourcingHandler.
     */
    public void markChangesAsCommitted(){
        this.changes.clear();
    }

    /**
     * applyChange invokes the apply method for a given event. This apply method todo
     */
    protected void applyChange(BaseEvent event, boolean isNewEvent){
        try {
            // Get the apply method through reflection
            var method = getClass().getDeclaredMethod("apply", event.getClass());

            // Make the method accessible
            method.setAccessible(true);

            // Makes sure that the correct apply method within the concrete aggregate is being called
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            logger.log(Level.WARNING,"The apply method was not found in the aggregate for "+event.getClass().getName());
        } catch (Exception e){
            logger.log(Level.SEVERE, "Error applying event to aggregate", e);
        } finally {
            if(isNewEvent){
                // Events that are new are added to changes so that they later will be published to the write database by the
                // eventStore by calling getUncommitedChanges()
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event){
        // ApplyChange to the event and mark it as true because the event is new when being called from raiseEvent.
        // This method is called from the AccountAggregate after a certain command is received
        applyChange(event, true);
    }

    /**
     * For each event, apply change, but with false as these are all the events that are used to rebuild the state of the aggregate.
     * Is being called from the AccountEventSourcingHandler to recreate the state of the aggregate *
     */
    public void replayEvents(Iterable<BaseEvent> events){
        events.forEach(event -> applyChange(event, false));
    }
}

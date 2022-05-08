package com.avantys.cqrs.core.events;

import com.avantys.cqrs.core.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The BaseEvent class extends the message class as this is a type of message.
 * An event describes that something has happened within the application. A typical source of an event is
 * the aggregate. When something happens within the aggregate, it will raise an event.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEvent extends Message {
    private int version; // This is important for replaying the event store to recreate the state of the aggregate.
}

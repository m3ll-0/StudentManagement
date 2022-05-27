package com.avantys.student.api.events;

import com.avantys.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The AcceptStudentEvent extends the BaseEvent class which extends the Message class.
 * The event resides in the common packages because both the query and command projects make use of this class.
 * This is the event that is raised after the FundsDepositedCommand is handled.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentAcceptedEvent extends BaseEvent {
    private boolean isAccepted;
}

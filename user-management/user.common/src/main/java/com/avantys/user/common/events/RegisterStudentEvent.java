package com.avantys.user.common.events;

import com.avantys.cqrs.core.events.BaseEvent;
import com.avantys.user.common.dto.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * The AccountOpenedEvent extends the BaseEvent class which extends the Message class.
 * The event resides in the common packages because both the query and command projects make use of this class.
 * This is the event that is raised after the OpenAccountCommand is handled.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RegisterStudentEvent extends BaseEvent {
    private String studentId;
    private PaymentMethod paymentMethod;
    private Date createdDate;
    private Boolean isAssessed;
    private Boolean isAccepted;
}

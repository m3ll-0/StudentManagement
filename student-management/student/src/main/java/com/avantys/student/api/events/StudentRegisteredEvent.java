package com.avantys.student.api.events;

import com.avantys.cqrs.core.events.BaseEvent;
import com.avantys.student.api.dto.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * The StudentRegisteredEvent extends the BaseEvent class which extends the Message class.
 * The event resides in the common packages because both the query and command projects make use of this class.
 * This is the event that is raised after the RegisterStudentCommand is handled.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentRegisteredEvent extends BaseEvent {
    private String studentId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String telephoneNr;
    private String zipCode;
    private String streetName;
    private String houseNumber;
    private PaymentMethod paymentMethod;
    private Date createdDate;
    private Boolean isAssessed;
    private Boolean isAccepted;
}

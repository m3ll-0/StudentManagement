package com.avantys.user.domain;

import com.avantys.user.api.commands.RegisterStudentCommand;
import com.avantys.user.api.dto.PaymentMethod;
import com.avantys.user.api.events.AcceptStudentEvent;
import com.avantys.cqrs.core.domain.AggregateRoot;
import com.avantys.user.api.events.AssessStudentEvent;
import com.avantys.user.api.events.AuthorizePaymentMethodEvent;
import com.avantys.user.api.events.StudentRegisteredEvent;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The StudentAggregate extends the AggregateRoot, and it is an entity or group of entities in DDD.
 */
@NoArgsConstructor
    public class StudentAggregate extends AggregateRoot {
    private boolean active;
    private boolean isAccepted;
    private boolean isAssessed;
    private PaymentMethod paymentMethod;

    /**
     * The RegisterStudentCommand creates the aggregate hence it is being handled in the constructor
     */
    public StudentAggregate(RegisterStudentCommand command){

        // Call the super class raiseEvent method and event from the aggregate to combine CQRS and event sourcing
        super.raiseEvent(StudentRegisteredEvent.builder()
                .id(command.getId())
                        .firstName(command.getFirstName())
                        .lastName(command.getLastName())
                        .dateOfBirth(command.getDateOfBirth())
                        .telephoneNr(command.getTelephoneNr())
                        .zipCode(command.getZipCode())
                        .streetName(command.getStreetName())
                        .houseNumber(command.getHouseNumber())
                .isAssessed(command.getIsAssessed())
                .isAccepted(command.getIsAccepted())
                .paymentMethod(command.getPaymentMethod())
                .createdDate(new Date())
                .build());
    }

    /**
     * Because reflection is used, multiple apply methods can be handled.
     * This apply method applies the StudentRegisteredEvent to the aggregate.
     */
    public void apply(StudentRegisteredEvent event){
        this.id = event.getId();
        this.active = true;
    }

    public void acceptStudent(boolean isAccepted ){

        this.isAccepted = true;

        super.raiseEvent(AcceptStudentEvent.builder()
                .id(this.id)
                .isAccepted(isAccepted)
                .build()
        );
    }

    public void apply(AcceptStudentEvent event){
        this.id = event.getId();
        this.isAccepted = event.isAccepted();
    }


    public void assessStudent(boolean isAssessed ){

        super.raiseEvent(AssessStudentEvent.builder()
                .id(this.id)
                .isAssessed(isAssessed)
                .build()
        );
    }

    public void apply(AssessStudentEvent event){
        this.id = event.getId();
        this.isAssessed = event.isAssessed();
    }

    public void authorizePaymentMethod(PaymentMethod paymentMethod){

        super.raiseEvent(AuthorizePaymentMethodEvent.builder()
                .id(this.id)
                .paymentMethod(paymentMethod)
                .build()
        );
    }

    public void apply(AuthorizePaymentMethodEvent event){
        this.id = event.getId();
        this.paymentMethod = event.getPaymentMethod();
    }
}

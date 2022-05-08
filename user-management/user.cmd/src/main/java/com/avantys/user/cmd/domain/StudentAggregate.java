package com.avantys.user.cmd.domain;

import com.avantys.user.cmd.api.commands.RegisterStudentCommand;
import com.avantys.user.common.events.AcceptStudentEvent;
import com.avantys.user.common.events.RegisterStudentEvent;
import com.avantys.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The AccountAggregate extends the AggregateRoot, and it is an entity or group of entities in DDD.
 */
@NoArgsConstructor
    public class StudentAggregate extends AggregateRoot {
    private boolean active;
    private boolean isAccepted;

    /**
     * The OpenAccountCommand creates the aggregate hence it is being handled in the constructor
     */
    public StudentAggregate(RegisterStudentCommand command){

        // Call the super class raiseEvent method and event from the aggregate to combine CQRS and event sourcing
        super.raiseEvent(RegisterStudentEvent.builder()
                .id(command.getId())
                .isAssessed(command.getIsAssessed())
                .isAccepted(command.getIsAccepted())
                .paymentMethod(command.getPaymentMethod())
                .createdDate(new Date())
                .build());
    }

    /**
     * Because reflection is used, multiple apply methods can be handled.
     * This apply method applies the AccountOpenedEvent to the aggregate.
     */
    public void apply(RegisterStudentEvent event){
        this.id = event.getId();
        this.active = true;
//        this.balance = event.getOpeningBalance();
    }

    public void acceptStudent(boolean isAccepted ){
        if(!this.active){
            throw new IllegalStateException("Funds cannot be deposited into a closed bank account");
        }

        // todo
        if(1 == 2){
            throw new IllegalStateException("The deposited amount must be greater than 0");
        } else {
            super.raiseEvent(AcceptStudentEvent.builder()
                    .id(this.id)
                    .isAccepted(isAccepted)
                    .build()
            );
        }
    }

    public void apply(AcceptStudentEvent event){
        this.id = event.getId();
        this.isAccepted = event.isAccepted();
    }
}
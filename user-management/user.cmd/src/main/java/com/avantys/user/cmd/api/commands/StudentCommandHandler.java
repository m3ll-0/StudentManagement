package com.avantys.user.cmd.api.commands;

import com.avantys.user.cmd.domain.StudentAggregate;
import com.avantys.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The AccountCommandHandler class implements the CommandHandler interface.
 * It is the concrete implementation of the colleague in the mediator pattern.
 */
@Service
public class StudentCommandHandler implements CommandHandler {

    // The eventSourcingHandler is used to replay the latest state of the aggregate, and to save the latest state of the aggregate.
    @Autowired
    private EventSourcingHandler<StudentAggregate> eventSourcingHandler;

    // Handle the openAccountCommand
    @Override
    public void handle(RegisterStudentCommand command) {
        var aggregate = new StudentAggregate(command);
        eventSourcingHandler.save(aggregate); // Save the aggregate
    }

    @Override
    public void handle(AcceptStudentCommand command) {
        // Get the latest state of the aggregate by invoking getById on the eventSourcingHandler
        // HIER
        var aggregate = eventSourcingHandler.getById(command.getId());
        command.setAccepted(true);
        aggregate.acceptStudent(command.isAccepted());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(AssessStudentCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        command.setAssessed(true);
        aggregate.assessStudent(command.isAssessed());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(AuthorizePaymentMethodCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        command.setPaymentMethod(command.getPaymentMethod());
        aggregate.authorizePaymentMethod (command.getPaymentMethod());
        eventSourcingHandler.save(aggregate);
    }
}

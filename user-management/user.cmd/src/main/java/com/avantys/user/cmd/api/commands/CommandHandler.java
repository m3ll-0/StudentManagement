package com.avantys.user.cmd.api.commands;

/**
 * The CommandHandler interface is an interface where all commands are registered.
 * This is the interface of the colleague in the Mediator pattern.
 */
public interface CommandHandler {
    void handle(RegisterStudentCommand command);

    void handle(AcceptStudentCommand command);
}
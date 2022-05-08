package com.avantys.cqrs.core.infrastructure;

import com.avantys.cqrs.core.commands.BaseCommand;
import com.avantys.cqrs.core.commands.CommandHandlerMethod;

/**
 * The CommandDispatcher interface is used to register commandHandlers and to send / dispatch commands.
 * This interface is the interface for the mediator in the mediator pattern.
 */
public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}

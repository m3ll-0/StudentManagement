package com.avantys.user.cmd.infrastructure;

import com.avantys.cqrs.core.commands.BaseCommand;
import com.avantys.cqrs.core.commands.CommandHandlerMethod;
import com.avantys.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The StudentCommandDispatcher class is the concrete implementation of the CommandDispatcher class.
 * It functions as the concrete mediator in the mediator pattern. It is responsible for
 */
@Service
public class StudentCommandDispatcher implements CommandDispatcher {
    // Maintains the colleague objects, in this case, a list of Commands and the respective CommandHandlerMethod
    // to map a relation between the command and the commandHandler.
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    /**
     * Register new handlers by adding them to routes.
     * The command handlers are set up after initializing the project in the CommandApplication class.
     */
    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    /**
     * Dispatches / sends commands to a registeredCommandHandler method.
     * This method is called from a controller and makes sure a command is being sent to the correct handler
     * after a certain API route is being requested.
     */
    @Override
    public void send(BaseCommand command) {
        // Get the correct handler from the command
        var handlers = routes.get(command.getClass());

        // Make sure that a certain commandHandler was found
        if(handlers == null || handlers.size() == 0){
            throw new RuntimeException("No command handler was registered.");
        }

        // Make sure that only one commandHandler was found
        if(handlers.size() > 1){
            throw new RuntimeException("Cannot send command to more than one handler.");
        }

        // Get the first element and call the handle() method with the command to handle a command.
        handlers.get(0).handle(command);
    }
}

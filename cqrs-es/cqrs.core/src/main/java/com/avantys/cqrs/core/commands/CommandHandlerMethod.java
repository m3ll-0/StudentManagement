package com.avantys.cqrs.core.commands;

/**
 *
 * The @FunctionalInterface annotation signifies that this interface only exhibits one functionality.
 * The interface uses a generic type that extends the BaseCommand so that it can handle all commands
 * That inherit from the BaseCommand class.
 */
@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand>  {
    void handle(T command); // T represents the command object or type.
}

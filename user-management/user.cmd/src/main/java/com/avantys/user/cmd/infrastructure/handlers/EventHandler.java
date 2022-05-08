package com.avantys.user.cmd.infrastructure.handlers;

import com.avantys.user.cmd.api.events.AcceptStudentEvent;
import com.avantys.user.cmd.api.events.StudentRegisteredEvent;

/**
 * EventHandler interface provides an interface abstraction through which events can be handled once they are consumed
 */
public interface EventHandler {
    void on(StudentRegisteredEvent event);
    void on(AcceptStudentEvent event);
}

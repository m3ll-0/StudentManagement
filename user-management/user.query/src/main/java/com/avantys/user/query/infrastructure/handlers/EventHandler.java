package com.avantys.user.query.infrastructure.handlers;

import com.avantys.user.common.events.AcceptStudentEvent;
import com.avantys.user.common.events.RegisterStudentEvent;

/**
 * EventHandler interface provides an interface abstraction through which events can be handled once they are consumed
 */
public interface EventHandler {
    void on(RegisterStudentEvent event);
    void on(AcceptStudentEvent event);
}

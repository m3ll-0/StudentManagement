package com.avantys.user.infrastructure.handlers;

import com.avantys.user.api.events.AcceptStudentEvent;
import com.avantys.user.api.events.AssessStudentEvent;
import com.avantys.user.api.events.AuthorizePaymentMethodEvent;
import com.avantys.user.api.events.StudentRegisteredEvent;

/**
 * EventHandler interface provides an interface abstraction through which events can be handled once they are consumed
 */
public interface EventHandler {
    void on(StudentRegisteredEvent event);
    void on(AcceptStudentEvent event);
    void on(AssessStudentEvent event);
    void on(AuthorizePaymentMethodEvent event);
}

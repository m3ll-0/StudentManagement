package com.avantys.student.infrastructure.handlers;

import com.avantys.student.api.events.StudentAcceptedEvent;
import com.avantys.student.api.events.StudentAssessedEvent;
import com.avantys.student.api.events.StudentPaymentMethodAuthorizedEvent;
import com.avantys.student.api.events.StudentRegisteredEvent;

/**
 * EventHandler interface provides an interface abstraction through which events can be handled once they are consumed
 */
public interface EventHandler {
    void on(StudentRegisteredEvent event);
    void on(StudentAcceptedEvent event);
    void on(StudentAssessedEvent event);
    void on(StudentPaymentMethodAuthorizedEvent event);
}

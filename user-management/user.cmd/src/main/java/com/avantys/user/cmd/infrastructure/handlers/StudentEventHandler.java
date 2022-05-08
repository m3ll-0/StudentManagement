package com.avantys.user.cmd.infrastructure.handlers;

import com.avantys.user.cmd.domain.Student;
import com.avantys.user.cmd.domain.StudentRepository;
import com.avantys.user.cmd.api.events.AcceptStudentEvent;
import com.avantys.user.cmd.api.events.RegisterStudentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AccountEventHandler implements the EventHandler interface.
 * Handle all events, and by doing so populate or alter the read database
 * Difference between eventHandler and eventSourcingHandler: The eventHandler resides at the query side and affects
 * the read database, the eventSourcingHandler resides in the command side
 */
@Service
public class StudentEventHandler implements EventHandler{
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Save a new record into the read database.
     * Called from the consume method in AccountEventConsumer after a new AccountOpenedEvent has been published.
     */
    @Override
    public void on(RegisterStudentEvent event) {
        var student = Student.builder()
                .studentId(event.getId())
                .paymentMethod(event.getPaymentMethod())
                .isAssessed(event.getIsAssessed())
                .isAccepted(event.getIsAccepted())
                .build();

        studentRepository.save(student);
    }

    /**
     * Instead of building a new entity, retrieve it from the repository (as it already exists) and save it to the accountRepository.
     */
    @Override
    public void on(AcceptStudentEvent event) {
        var student = studentRepository.findById(event.getId());

        if(student.isEmpty()){
            return;
        }

        // todo
        var isAccepted = student.get().isAccepted();
        student.get().setAccepted(isAccepted);
        studentRepository.save(student.get());
    }
}

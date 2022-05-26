package com.avantys.student.infrastructure.handlers;

import com.avantys.student.api.events.AssessStudentEvent;
import com.avantys.student.api.events.AuthorizePaymentMethodEvent;
import com.avantys.student.api.events.StudentRegisteredEvent;
import com.avantys.student.domain.Student;
import com.avantys.student.domain.StudentRepository;
import com.avantys.student.api.events.AcceptStudentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentEventHandler implements the EventHandler interface.
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
     * Called from the consume method in StudentEventConsumer after a new StudentRegisteredEvent has been published.
     */
    @Override
    public void on(StudentRegisteredEvent event) {
        var student = Student.builder()
                .studentId(event.getId())
                .paymentMethod(event.getPaymentMethod())
                .isAssessed(event.getIsAssessed())
                .isAccepted(event.getIsAccepted())
                .build();

        studentRepository.save(student);
    }

    /**
     * Instead of building a new entity, retrieve it from the repository (as it already exists) and save it to the studentRepository.
     */
    @Override
    public void on(AcceptStudentEvent event) {
        var student = studentRepository.findById(event.getId());

        if(student.isEmpty()){
            return;
        }

        student.get().setAccepted(true);
        studentRepository.save(student.get());
    }

    @Override
    public void on(AssessStudentEvent event) {
        var student = studentRepository.findById(event.getId());

        if(student.isEmpty()){
            return;
        }

        student.get().setAssessed(true);
        studentRepository.save(student.get());
    }

    @Override
    public void on(AuthorizePaymentMethodEvent event) {
        var student = studentRepository.findById(event.getId());

        if(student.isEmpty()){
            return;
        }

        student.get().setPaymentMethod(event.getPaymentMethod());
        studentRepository.save(student.get());
    }
}

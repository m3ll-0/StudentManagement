//package com.avantys.student.infrastructure.consumers;
//
//import com.avantys.student.api.events.StudentAssessedEvent;
//import com.avantys.student.api.events.StudentPaymentMethodAuthorizedEvent;
//import com.avantys.student.api.events.StudentRegisteredEvent;
//import com.avantys.student.infrastructure.handlers.EventHandler;
//import com.avantys.student.api.events.StudentAcceptedEvent;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
///**
// * StudentEventConsumer implements the EventConsumer interface.
// * Is responsible for consuming events from the message broker, and then calling the appropriate eventHandler which will
// * in turn save an event to the read database.
// */
//@Service
//public class KafkaStudentEventConsumer implements EventConsumer{
//    @Autowired
//    private EventHandler eventHandler;
//
//    @KafkaListener(topics = "StudentRegisteredEvent", groupId = "${spring.kafka.consumer.group-id}")
//    @Override
//    public void consume(StudentRegisteredEvent event) {
//        eventHandler.on(event);
//    }
//
//    @KafkaListener(topics = "StudentAcceptedEvent", groupId = "${spring.kafka.consumer.group-id}")
//    @Override
//    public void consume(StudentAcceptedEvent event) {
//        eventHandler.on(event);
//    }
//
//    @KafkaListener(topics = "StudentAssessedEvent", groupId = "${spring.kafka.consumer.group-id}")
//    @Override
//    public void consume(StudentAssessedEvent event) {
//        eventHandler.on(event);
//    }
//
//    @KafkaListener(topics = "StudentPaymentMethodAuthorizedEvent", groupId = "${spring.kafka.consumer.group-id}")
//    @Override
//    public void consume(StudentPaymentMethodAuthorizedEvent event) {
//        eventHandler.on(event);
//    }
//}

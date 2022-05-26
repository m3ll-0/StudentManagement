package com.avantys.student.api.controllers;

import com.avantys.student.api.dto.StudentLookupResponse;
import com.avantys.student.api.queries.FindStudentByIdQuery;
import com.avantys.student.api.queries.FindAllStudentsQuery;
import com.avantys.student.domain.Student;
import com.avantys.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/studentLookup")
public class LookupStudentController {
    private final Logger logger = Logger.getLogger(LookupStudentController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "/")
    public ResponseEntity<StudentLookupResponse> getAllStudents(){
        try {
            List<Student> studentList = queryDispatcher.send(new FindAllStudentsQuery());

            if(studentList == null || studentList.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = StudentLookupResponse.builder()
                    .students(studentList)
                    .message("Successfully returned  students")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            var safeErrorMessage = "Failed to complete request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new StudentLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/ById/{id}")
    public ResponseEntity<StudentLookupResponse> getStudentById(@PathVariable(value = "id") String id){
        try {
            List<Student> studentList = queryDispatcher.send(new FindStudentByIdQuery(id));

            if(studentList == null || studentList.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = StudentLookupResponse.builder()
                    .students(studentList)
                    .message("Succesfully returned student")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            var safeErrorMessage = "Failed to complete getbyid request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new StudentLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

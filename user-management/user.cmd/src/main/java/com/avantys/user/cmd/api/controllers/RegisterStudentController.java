package com.avantys.user.cmd.api.controllers;

import com.avantys.user.cmd.api.commands.RegisterStudentCommand;
import com.avantys.user.cmd.api.dto.RegisterStudentResponse;
import com.avantys.cqrs.core.infrastructure.CommandDispatcher;
import com.avantys.user.cmd.api.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/registerStudent")
public class RegisterStudentController {
    private final Logger logger = Logger.getLogger(RegisterStudentController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody RegisterStudentCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try{
            commandDispatcher.send(command);
            return new ResponseEntity<>(new RegisterStudentResponse("Bank account creation request completed.", id), HttpStatus.CREATED);
        } catch (IllegalStateException e){
            logger.log(Level.WARNING, "Client made a bad request - " + e.toString());
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            logger.log(Level.SEVERE,"Error while processing request to open a new account");
            return new ResponseEntity<>(new RegisterStudentResponse("Error while processing request to open a new account", id), HttpStatus.BAD_REQUEST);
        }
    }
}

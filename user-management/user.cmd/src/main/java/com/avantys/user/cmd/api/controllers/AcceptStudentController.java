package com.avantys.user.cmd.api.controllers;

import com.avantys.user.cmd.api.commands.AcceptStudentCommand;
import com.avantys.cqrs.core.infrastructure.CommandDispatcher;
import com.avantys.user.cmd.api.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/depositFunds")
public class AcceptStudentController {
    private final Logger logger = Logger.getLogger(AcceptStudentController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id,
                                                     @RequestBody AcceptStudentCommand command
    ){
        try{
            command.setId(id);
            commandDispatcher.send(command); // Send command to registered command handlers
            return new ResponseEntity<>(new BaseResponse("Deposit funds completed."), HttpStatus.CREATED);
        } catch (IllegalStateException e){ // Handle client errors
            logger.log(Level.WARNING, "Client made a bad request - " + e.toString());
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e){ // Internal server errors
            logger.log(Level.SEVERE,"Error while processing request to deposit funds");
            return new ResponseEntity<>(new BaseResponse("Error while processing request to deposit"), HttpStatus.BAD_REQUEST);
        }

    }


}

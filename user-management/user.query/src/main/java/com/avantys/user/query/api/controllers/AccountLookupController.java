package com.avantys.user.query.api.controllers;

import com.avantys.user.query.api.dto.AccountLookupResponse;
import com.avantys.user.query.api.dto.EqualityType;
import com.avantys.user.query.domain.Student;
import com.avantys.user.query.api.queries.FindStudentByStudentIdQuery;
import com.avantys.user.query.api.queries.FindAccountWithBalanceQuery;
import com.avantys.user.query.api.queries.FindAccountsByIdQuery;
import com.avantys.user.query.api.queries.FindAllAccountsQuery;
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
@RequestMapping(path = "/api/v1/bankaccountLookup")
public class AccountLookupController {
    private final Logger logger = Logger.getLogger(AccountLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "/")
    public ResponseEntity<AccountLookupResponse> getAllAccount(){
        try {
            List<Student> accountList = queryDispatcher.send(new FindAllAccountsQuery());

            if(accountList == null || accountList.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message("Succesfully returned todo bankAccounts")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            var safeErrorMessage = "Failed to complete request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/ById/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountsById(@PathVariable(value = "id") String id){
        try {
            List<Student> accountList = queryDispatcher.send(new FindAccountsByIdQuery(id));

            if(accountList == null || accountList.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message("Succesfully returned bank account")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            var safeErrorMessage = "Failed to complete getbyid request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byHolder/{accountHolder}")
    public ResponseEntity<AccountLookupResponse> getAccountByHolder(@PathVariable(value = "accountHolder") String accountHolder){
        try {
            List<Student> accountList = queryDispatcher.send(new FindStudentByStudentIdQuery(accountHolder));

            if(accountList == null || accountList.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message("Succesfully returned bank account")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            var safeErrorMessage = "Failed to complete getaccountbyholder request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/withBalance/{equalityType}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountWithBalance(@PathVariable(value = "equalityType") EqualityType equalityType,
                                                                       @PathVariable(value = "balance") double balance
                                                                       ){
        try {
            List<Student> accountList = queryDispatcher.send(new FindAccountWithBalanceQuery(equalityType, balance));

            if(accountList == null || accountList.size() == 0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookupResponse.builder()
                    .accounts(accountList)
                    .message("Succesfully returned bank account")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            var safeErrorMessage = "Failed to complete with balance request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

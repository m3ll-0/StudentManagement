package com.avantys.student;

import com.avantys.cqrs.core.infrastructure.QueryDispatcher;
import com.avantys.student.api.commands.*;
import com.avantys.cqrs.core.infrastructure.CommandDispatcher;
import com.avantys.student.api.queries.FindAllStudentsQuery;
import com.avantys.student.api.queries.FindStudentByIdQuery;
import com.avantys.student.api.queries.QueryHandler;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	// Tell spring to execute the method after the initialization of the bean properties
	@PostConstruct
	public void registerHandlers(){
		// Register command handlers
		commandDispatcher.registerHandler(RegisterStudentCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(AcceptStudentCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(AssessStudentCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(AuthorizePaymentMethodCommand.class, commandHandler::handle);

		// Register query handlers
		queryDispatcher.registerHandler(FindAllStudentsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindStudentByIdQuery.class, queryHandler::handle);
	}
}
package com.avantys.user.cmd;

import com.avantys.cqrs.core.infrastructure.QueryDispatcher;
import com.avantys.user.cmd.api.commands.*;
import com.avantys.cqrs.core.infrastructure.CommandDispatcher;
import com.avantys.user.cmd.api.queries.FindStudentByStudentIdQuery;
import com.avantys.user.cmd.api.queries.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	@PostConstruct // Tell spring to execute the method after the initialization of the bean properties
	public void registerHandlers(){
		commandDispatcher.registerHandler(RegisterStudentCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(AcceptStudentCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(AssessStudentCommand.class, commandHandler::handle);
	}

	@PostConstruct
	public void registerHandlersQuery(){
		queryDispatcher.registerHandler(FindStudentByStudentIdQuery.class, queryHandler::handle);
	}
}

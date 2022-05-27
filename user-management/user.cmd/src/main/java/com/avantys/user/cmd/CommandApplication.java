package com.avantys.user.cmd;

import com.avantys.user.cmd.api.commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDis commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct // Tell spring to execute the method after the initialization of the bean properties
	public void registerHandlers(){
		commandDispatcher.registerHandler(RegisterStudentCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(AcceptStudentCommand.class, commandHandler::handle);
	}
}

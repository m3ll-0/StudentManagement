package com.avantys.user.cmd.api.commands;

import com.avantys.cqrs.core.commands.BaseCommand;
import lombok.Data;

/**
 * DepositFundsCommand class is a concrete implementation of the BaseCommand class that inherits from Message class.
 * The Lombok @Data annotation provides getters and setters.
 */
@Data
public class AcceptStudentCommand extends BaseCommand {
    private boolean isAccepted;
}

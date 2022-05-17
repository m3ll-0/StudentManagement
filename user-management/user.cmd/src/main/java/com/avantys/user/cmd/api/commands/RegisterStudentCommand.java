package com.avantys.user.cmd.api.commands;

import com.avantys.cqrs.core.commands.BaseCommand;
import com.avantys.user.cmd.api.dto.PaymentMethod;
import lombok.Data;

/**
 * RegisterStudentCommand class is a concrete implementation of the BaseCommand class that inherits from Message class.
 * The Lombok @Data annotation provides getters and setters.
 */
@Data
public class RegisterStudentCommand extends BaseCommand {
    private String studentId;
    private PaymentMethod paymentMethod;
    private Boolean isAssessed;
    private Boolean isAccepted;
}

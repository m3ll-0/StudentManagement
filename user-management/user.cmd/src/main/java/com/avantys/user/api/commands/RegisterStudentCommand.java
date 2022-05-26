package com.avantys.user.api.commands;

import com.avantys.cqrs.core.commands.BaseCommand;
import com.avantys.user.api.dto.PaymentMethod;
import lombok.Data;

/**
 * RegisterStudentCommand class is a concrete implementation of the BaseCommand class that inherits from Message class.
 * The Lombok @Data annotation provides getters and setters.
 */
@Data
public class RegisterStudentCommand extends BaseCommand {
    private String studentId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String telephoneNr;
    private String zipCode;
    private String streetName;
    private String houseNumber;
    private PaymentMethod paymentMethod;
    private Boolean isAssessed;
    private Boolean isAccepted;
}

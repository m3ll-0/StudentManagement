package com.avantys.user.cmd.api.commands;

import com.avantys.cqrs.core.commands.BaseCommand;
import com.avantys.user.cmd.api.dto.PaymentMethod;
import lombok.Data;

@Data
public class AuthorizePaymentMethodCommand extends BaseCommand {
    private PaymentMethod paymentMethod;
}

package com.avantys.user.api.commands;

import com.avantys.cqrs.core.commands.BaseCommand;
import com.avantys.user.api.dto.PaymentMethod;
import lombok.Data;

@Data
public class AuthorizePaymentMethodCommand extends BaseCommand {
    private PaymentMethod paymentMethod;
}

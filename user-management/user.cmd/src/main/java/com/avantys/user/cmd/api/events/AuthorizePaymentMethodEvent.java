package com.avantys.user.cmd.api.events;

import com.avantys.cqrs.core.events.BaseEvent;
import com.avantys.user.cmd.api.dto.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthorizePaymentMethodEvent extends BaseEvent{
    private PaymentMethod paymentMethod;
}

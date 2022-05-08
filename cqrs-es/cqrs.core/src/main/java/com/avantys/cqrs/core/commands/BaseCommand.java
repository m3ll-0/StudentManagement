package com.avantys.cqrs.core.commands;

import com.avantys.cqrs.core.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The BaseCommand class extends the message class, this is a type of message.
 */
@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id){
        super(id);
    }
}

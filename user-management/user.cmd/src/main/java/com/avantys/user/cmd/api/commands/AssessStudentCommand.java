package com.avantys.user.cmd.api.commands;

import com.avantys.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class AssessStudentCommand extends BaseCommand {
    private boolean isAssessed;
}
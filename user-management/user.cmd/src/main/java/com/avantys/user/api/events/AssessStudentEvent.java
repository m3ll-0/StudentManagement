package com.avantys.user.api.events;

import com.avantys.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AssessStudentEvent extends BaseEvent {
    private boolean isAssessed;
}
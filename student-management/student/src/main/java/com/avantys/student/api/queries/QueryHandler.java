package com.avantys.student.api.queries;

import com.avantys.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllStudentsQuery query);
    List<BaseEntity> handle(FindStudentByIdQuery query);
}

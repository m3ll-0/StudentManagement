package com.avantys.user.query.api.queries;

import com.avantys.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindStudentByStudentIdQuery extends BaseQuery {
    private String studentId;
}

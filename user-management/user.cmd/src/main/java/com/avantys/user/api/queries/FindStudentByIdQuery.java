package com.avantys.user.api.queries;

import com.avantys.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindStudentByIdQuery extends BaseQuery {
    private String id;
}

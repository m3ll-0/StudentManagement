package com.avantys.user.cmd.api.queries;

import com.avantys.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountsByIdQuery extends BaseQuery {
    private String id;
}

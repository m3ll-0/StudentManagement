package com.avantys.user.query.api.queries;

import com.avantys.user.query.api.dto.EqualityType;
import com.avantys.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}

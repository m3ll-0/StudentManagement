package com.avantys.user.cmd.api.queries;

import com.avantys.cqrs.core.queries.BaseQuery;
import com.avantys.user.cmd.api.dto.EqualityType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}

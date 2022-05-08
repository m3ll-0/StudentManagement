package com.avantys.user.cmd.api.queries;

import com.avantys.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
//    List<BaseEntity> handle(FindAllAccountsQuery query);
    List<BaseEntity> handle(FindStudentByStudentIdQuery query);
//    List<BaseEntity> handle(FindAccountByHolderQuery query);
//    List<BaseEntity> handle(FindAccountWithBalanceQuery query);
}

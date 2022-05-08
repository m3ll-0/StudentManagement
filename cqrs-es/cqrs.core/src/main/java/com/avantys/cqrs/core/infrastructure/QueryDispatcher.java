package com.avantys.cqrs.core.infrastructure;

import com.avantys.cqrs.core.domain.BaseEntity;
import com.avantys.cqrs.core.queries.BaseQuery;
import com.avantys.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}

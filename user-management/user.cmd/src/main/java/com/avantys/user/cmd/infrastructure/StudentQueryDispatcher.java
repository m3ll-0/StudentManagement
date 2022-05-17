package com.avantys.user.cmd.infrastructure;

import com.avantys.cqrs.core.domain.BaseEntity;
import com.avantys.cqrs.core.infrastructure.QueryDispatcher;
import com.avantys.cqrs.core.queries.BaseQuery;
import com.avantys.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * StudentQueryDispatcher implements the queryDispatcher interface.
 * It is the concrete implementation of the mediator queryDispatcher.
 */
@Service
public class StudentQueryDispatcher implements QueryDispatcher {
    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c-> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if(handlers.isEmpty() || handlers == null){
            throw new RuntimeException("No query handlers registered");
        }

        if(handlers.size() > 1 ){
            throw new RuntimeException("Cannot send query to more than one hanlder");
        }

        return handlers.get(0).handle(query);
    }
}

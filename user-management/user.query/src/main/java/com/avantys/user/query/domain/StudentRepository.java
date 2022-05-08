package com.avantys.user.query.domain;

import com.avantys.cqrs.core.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByStudentId(String accountHolder);
//    List<BaseEntity> findByBalanceGreaterThan(double balance);
//    List<BaseEntity> findByBalanceLessThan(double balance);
}

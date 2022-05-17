package com.avantys.user.cmd.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByStudentId(String studentId);
}

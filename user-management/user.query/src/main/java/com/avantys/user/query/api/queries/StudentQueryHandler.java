package com.avantys.user.query.api.queries;

import com.avantys.user.query.domain.StudentRepository;
import com.avantys.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentQueryHandler implements QueryHandler{
    @Autowired
    private StudentRepository studentRepository;

//    @Override
//    public List<BaseEntity> handle(FindAllAccountsQuery query) {
//        Iterable<Student> students = studentRepository.findAll();
//        List<BaseEntity> studentList = new ArrayList<>();
//        students.forEach(studentList::add);
//        return studentList;
//    }

//    @Override
//    public List<BaseEntity> handle(FindAccountsByIdQuery query) {
//        var student  = studentRepository.findById(query.getId());
//        if(student.isEmpty()){
//            return null;
//        }
//
//        List<BaseEntity> studentList  = new ArrayList<>();
//        studentList.add(student.get());
//        return studentList;
//    }

    @Override
    public List<BaseEntity> handle(FindStudentByStudentIdQuery query) {
        var student  = studentRepository.findByStudentId(query.getStudentId());
        if(student.isEmpty()){
            return null;
        }

        List<BaseEntity> StudentList  = new ArrayList<>();
        StudentList.add(student.get());
        return StudentList;
    }

//    @Override
//    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
//
//        List<BaseEntity> studentList = query.getEqualityType() == EqualityType.GREATER_THAN
//                ? studentRepository.findByBalanceGreaterThan(query.getBalance())
//                : studentRepository.findByBalanceLessThan(query.getBalance());
//
//        return studentList;
//    }
}
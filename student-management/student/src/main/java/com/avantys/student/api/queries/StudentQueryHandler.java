package com.avantys.student.api.queries;

import com.avantys.cqrs.core.domain.BaseEntity;
import com.avantys.student.domain.Student;
import com.avantys.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentQueryHandler implements QueryHandler{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<BaseEntity> handle(FindAllStudentsQuery query) {
        Iterable<Student> students = studentRepository.findAll();
        List<BaseEntity> studentList = new ArrayList<>();
        students.forEach(studentList::add);
        return studentList;
    }

    @Override
    public List<BaseEntity> handle(FindStudentByIdQuery query) {
        var student  = studentRepository.findByStudentId(query.getId());
        if(student.isEmpty()){
            return null;
        }

        List<BaseEntity> StudentList  = new ArrayList<>();
        StudentList.add(student.get());
        return StudentList;
    }
}

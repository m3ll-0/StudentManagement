package com.avantys.student.api.dto;

import com.avantys.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentLookupResponse extends BaseResponse {
    private List<Student> students;

    public StudentLookupResponse(String message){
        super(message);
    }
}

package com.avantys.user.query.api.dto;

import com.avantys.user.common.dto.BaseResponse;
import com.avantys.user.query.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLookupResponse extends BaseResponse {
    private List<Student> accounts;

    public AccountLookupResponse(String message){
        super(message);
    }
}

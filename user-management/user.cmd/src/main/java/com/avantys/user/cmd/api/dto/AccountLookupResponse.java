package com.avantys.user.cmd.api.dto;

import com.avantys.user.cmd.domain.Student;
import com.avantys.user.common.dto.BaseResponse;
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

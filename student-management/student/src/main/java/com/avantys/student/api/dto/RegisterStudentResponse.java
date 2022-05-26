package com.avantys.student.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The response that will be sent back by the controller
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentResponse extends BaseResponse {
    private String id;

    public RegisterStudentResponse(String message, String id){
        super(message);
        this.id = id;
    }

}

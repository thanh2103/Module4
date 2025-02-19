package com.techzen.academy_n1224c1_;

import com.techzen.academy_n1224c1_.dto.ApiResponse;
import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
@ControllerAdvice
public class Employee_GolbalExceptionHandle {

    public ResponseEntity<?> hanResponse(ApiException e){
        Errorcode errorcode = e.getErrorcode();
        return ResponseEntity.status(Errorcode.EMPLOYEE_NOT_EXIST.getHttpStatus()).body(ApiResponse.<Employee>builder()
                .code(Errorcode.EMPLOYEE_NOT_EXIST.getCode())
                .message("Employee not exist")
                .build());
    }
}

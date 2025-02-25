package com.techzen.academy_n1224c1_;

import com.techzen.academy_n1224c1_.dto.ApiResponse;
import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import com.techzen.academy_n1224c1_.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// lắng nghe các sự kiện

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        Errorcode errorcode = e.getErrorcode();
        return ResponseEntity.status(Errorcode.STUDENT_NOT_EXITS.getHttpStatus()).body(ApiResponse.<Student>builder()
                .code(Errorcode.STUDENT_NOT_EXITS.getCode())
                .message(Errorcode.STUDENT_NOT_EXITS.getMessage())
                .build());

    }
}

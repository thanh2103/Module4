package com.techzen.academy_n1224c1_.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Errorcode {
    STUDENT_NOT_EXITS(40401,"Student is not exist",HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXITS(40402,"Teacher is not exist",HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_EXIST(40403,"Employee is not exist",HttpStatus.NOT_FOUND),

    ;//ngăn cách
    int code;
    String message;
    HttpStatus httpStatus;

}

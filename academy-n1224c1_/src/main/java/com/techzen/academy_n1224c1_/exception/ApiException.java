package com.techzen.academy_n1224c1_.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
//xử lý những trường hợp ngoại lệ
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class ApiException extends RuntimeException {
    Errorcode  errorcode;
    public ApiException(Errorcode errorcode) {
        super(errorcode.getMessage());
        this.errorcode = errorcode;
    }
}

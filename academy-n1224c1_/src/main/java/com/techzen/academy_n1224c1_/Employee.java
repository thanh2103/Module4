package com.techzen.academy_n1224c1_;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    int id;
    String ten;
    LocalDate ngaysinh;
    Gender gioitinh;
    double luong;

    public enum Gender {
        Male,
        Female
    }
}

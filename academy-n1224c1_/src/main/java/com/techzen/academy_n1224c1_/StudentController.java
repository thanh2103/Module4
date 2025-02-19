package com.techzen.academy_n1224c1_;

import com.techzen.academy_n1224c1_.dto.ApiResponse;
import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final RestClient.Builder builder;
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Thanh", 8.0),
                    new Student(2, "Hai", 8.0)
            )
    );

    public StudentController(RestClient.Builder builder) {
        this.builder = builder;
    }

//    //   @RequestMapping("/liststudent")
//    @GetMapping
//    public List<Student> getStudents() {
//        return students;
//    }

    //THÊM MỚI
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId((int) (Math.random() * 1000));
        students.add(student);
        //return ResponseEntity.status(201).body(student);
        //return ResponseEntity.status(HttpStatus.OK).body(student);
        return ResponseEntity.ok(student);
    }

    //--------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {
        for (Student stu : students) {
            if (stu.getId() == id) {
                return ResponseEntity.ok(ApiResponse.<Student>builder()
                        .data(stu)
                        .build());
            }
        }
        throw new ApiException(Errorcode.STUDENT_NOT_EXITS);
//        return ResponseEntity.status(Errorcode.STUDENT_NOT_EXITS.getHttpStatus()).body(ApiResponse.<Student>builder()
//                .code(Errorcode.STUDENT_NOT_EXITS.getCode())
//                .message(Errorcode.STUDENT_NOT_EXITS.getMessage())
//                .build());
    }

    //--------------
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> searchStudent(@RequestParam(defaultValue = "") String name) {
        List<Student> studentsearch = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().contains(name)) {
                studentsearch.add(student);
            }
        }
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .data(studentsearch)
                .build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        //boolean isExit=false;
        for (Student stud : students) {
            if (stud.getId() == id) {
                stud.setName(student.getName());
                stud.setScore(student.getScore());
                return ResponseEntity.ok(ApiResponse.<Student>builder()
                        .data(stud)
                        .build());
            }
        }
        return ResponseEntity.status(Errorcode.STUDENT_NOT_EXITS.getHttpStatus()).body(ApiResponse.<Student>builder()
                .code(Errorcode.STUDENT_NOT_EXITS.getCode())
                .message(Errorcode.STUDENT_NOT_EXITS.getMessage())
                .build());
    }
}

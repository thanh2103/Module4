package com.techzen.academy_n1224c1_.repository;

import com.techzen.academy_n1224c1_.dto.ApiResponse;
import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import com.techzen.academy_n1224c1_.model.Student;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Getter
@Scope("prototype")//
public class StudentRepository implements IStudentRepository {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Thanh", 8.0),
                    new Student(2, "Hai", 8.0)
            )
    );

    public List<Student> getStudents(String name) {
        List<Student> studentsearch = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().contains(name)) {
                studentsearch.add(student);
            }
        }
        return studentsearch;
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Student addStudent(Student student) {
        student.setId(students.size() + 1);
        students.add(student);
        return student;
    }

    public Student updateStudent(Student student) {
        for (Student stud : students) {
            if (stud.getId() == student.getId()) {
                stud.setName(student.getName());
                stud.setScore(student.getScore());
                return stud;
            }
        }
        throw new ApiException(Errorcode.STUDENT_NOT_EXITS);
    }

    public Student deleteStudent(int id) {
        for (Student stud : students) {
            if (stud.getId() == id) {
                students.remove(stud);
            }
        }
        throw new ApiException(Errorcode.STUDENT_NOT_EXITS);
    }
}

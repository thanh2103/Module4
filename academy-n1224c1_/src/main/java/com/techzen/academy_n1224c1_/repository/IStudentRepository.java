package com.techzen.academy_n1224c1_.repository;

import com.techzen.academy_n1224c1_.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> getStudents(String name);

    Student findById(int id);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    Student deleteStudent(int id);
}

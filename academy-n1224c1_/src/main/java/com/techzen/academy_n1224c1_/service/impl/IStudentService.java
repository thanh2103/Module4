package com.techzen.academy_n1224c1_.service.impl;

import com.techzen.academy_n1224c1_.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getStudents(String name);

    Student findById(int id);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    Student deleteStudent(int id);
}

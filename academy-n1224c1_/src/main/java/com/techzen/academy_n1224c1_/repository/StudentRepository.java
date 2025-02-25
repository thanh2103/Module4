package com.techzen.academy_n1224c1_.repository;

import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import com.techzen.academy_n1224c1_.model.Student;
import com.techzen.academy_n1224c1_.repository.base.BaseRepository;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from student where name like concat('%',?,'%')");
            preparedStatement.setString(1, name);// 1 là chỉ số index của dấu chấn hỏi

            ResultSet resulset = preparedStatement.executeQuery();

            while (resulset.next()) {
                studentsearch.add(Student.builder()
                        .id(resulset.getInt("id"))
                        .name(resulset.getString("name"))
                        .score(resulset.getDouble("score"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentsearch;
    }

    public Student findById(int id) {

        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from student where id= ?");
            preparedStatement.setInt(1, id);// 1 là chỉ số index của dấu chấn hỏi

//            executeQuery() chỉ dùng cho lệnh SELECT. Nếu dùng với INSERT, UPDATE hoặc DELETE sẽ bị lỗi.
//                    Luôn dùng resultSet.next() để duyệt qua các dòng dữ liệu.
            ResultSet resulset = preparedStatement.executeQuery();

            if (resulset.next()) {
                return Student.builder()
                        .id(resulset.getInt("id"))
                        .name(resulset.getString("name"))
                        .score(resulset.getDouble("score"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public Student addStudent(Student student) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("insert into student(name,score) value (?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDouble(1, student.getScore());
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return student;
    }

    public Student updateStudent(Student student) {
        try {
            if (findById(student.getId()) == null) {
                PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("insert into student(name,score) value (?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, student.getName());
                preparedStatement.setDouble(1, student.getScore());
                preparedStatement.executeUpdate();//executeUpdate()
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    student.setId(rs.getInt(1));
                }
            } else {
                PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("update student set name=?, score=? where id=?");
                preparedStatement.setString(1, student.getName());
                preparedStatement.setDouble(1, student.getScore());
                preparedStatement.setInt(1, student.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

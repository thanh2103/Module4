package com.techzen.academy_n1224c1_.repository;

import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import com.techzen.academy_n1224c1_.model.Employee;
import com.techzen.academy_n1224c1_.model.Student;
import com.techzen.academy_n1224c1_.repository.base.BaseRepository;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
@Getter
@Scope("prototype")
public class EmployeeRepository implements IEmployeeRepository{
    private List<Employee> employees = new ArrayList<Employee>(
            Arrays.asList(
                    new Employee(1, "thanh", LocalDate.of(2003, 11, 22), Employee.Gender.Female, 14000000),
                    new Employee(2, "linh", LocalDate.of(2003, 7, 4), Employee.Gender.Female, 15000000),
                    new Employee(3, "Hai", LocalDate.of(2005, 9, 30), Employee.Gender.Male, 15000000)
            )

    );

    public List<Employee> getEmployee(String name) {
        List<Employee> employeeSearch = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from employee where name like concat('%',?,'%')");
            preparedStatement.setString(1, name);// 1 là chỉ số index của dấu chấn hỏi

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeSearch.add(Employee.builder()
                        .id(resultSet.getInt("id"))
                        .ten(resultSet.getString("name"))
                        .ngaysinh(resultSet.getDate("datetime").toLocalDate()) // Sửa đúng
                        .gioitinh(Employee.Gender.valueOf(resultSet.getString("gender")))
                        .build());
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employeeSearch;
    }


//    public Employee findById(int id) {
//        try {
//            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from employee where id =?");
//            preparedStatement.setInt(1, id);// 1 là chỉ số index của dấu chấn hỏi
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return Employee.builder()
//                        .ten(resultSet.getString("name"))
//                        .ngaysinh(resultSet.getDate("datetime").toLocalDate()) // Sửa đúng
//                        .gioitinh(Employee.Gender.valueOf(resultSet.getString("gender")))
//                        .luong(resultSet.getDouble("salary"))
//                        .build();
//            }
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
public Employee findById(int id) {
    try {
        PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("SELECT id, name, datetime, gender, salary FROM employee WHERE id = ?");
        // Thiết lập giá trị tham số ID trong câu truy vấn
        preparedStatement.setInt(1, id); // 1 là chỉ số của dấu hỏi

        // Thực hiện câu truy vấn và lấy tập kết quả
        ResultSet resultSet = preparedStatement.executeQuery();
        // Nếu tìm thấy nhân viên, tạo và trả về đối tượng Employee
        while (resultSet.next()) {
            return Employee.builder()
                    .id(resultSet.getInt("id"))
                    .ten(resultSet.getString("name"))
                    .ngaysinh(resultSet.getDate("datetime").toLocalDate()) // Chuyển đổi đúng
                    .gioitinh(Employee.Gender.valueOf(resultSet.getString("gender").toUpperCase()))
                    .luong(resultSet.getDouble("salary"))
                    .build();
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ SQL
        throw new RuntimeException(e);
    }

    // Trả về null nếu không tìm thấy nhân viên
    return null;
}

//    public Employee findById(int id) {
//        try {
//            PreparedStatement preparedStatement = BaseReponsitory.getConnection()
//                    .prepareStatement("SELECT id, name, date, gender, salary FROM employee WHERE id = ?");
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                return Employee.builder()
//                        .id(resultSet.getInt("id"))
//                        .name(resultSet.getString("name"))
//                        .date(resultSet.getDate("date").toLocalDate())
//                        .gender(Employee.Gender.valueOf(resultSet.getString("gender").toUpperCase()))
//                        .salary(resultSet.getDouble("salary"))
//                        .build();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }

    public Employee addEmployee(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        for (Employee emp : employees) {
            if (emp.getId() == employee.getId()) {
                emp.setTen(employee.getTen());
                emp.setGioitinh(employee.getGioitinh());
                emp.setNgaysinh(employee.getNgaysinh());
                emp.setLuong(employee.getLuong());
                return emp;
            }
        }
        throw new ApiException(Errorcode.EMPLOYEE_NOT_EXIST);
    }

    public Employee deleteEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
            }
        }
        throw new ApiException(Errorcode.EMPLOYEE_NOT_EXIST);
    }
}

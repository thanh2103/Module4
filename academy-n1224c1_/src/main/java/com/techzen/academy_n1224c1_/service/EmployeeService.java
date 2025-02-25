package com.techzen.academy_n1224c1_.service;

import com.techzen.academy_n1224c1_.model.Employee;
import com.techzen.academy_n1224c1_.repository.EmployeeRepository;
import com.techzen.academy_n1224c1_.repository.IEmployeeRepository;
import com.techzen.academy_n1224c1_.service.impl.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Scope("singleton")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService{
    IEmployeeRepository employeeRepository;

    public List<Employee> getEmployee(String name) {
        return employeeRepository.getEmployee(name);

    }

    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    public Employee deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }
}

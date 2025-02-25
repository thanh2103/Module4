package com.techzen.academy_n1224c1_.repository;

import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import com.techzen.academy_n1224c1_.model.Employee;

import java.util.ArrayList;
import java.util.List;

public interface IEmployeeRepository {
    public List<Employee> getEmployee(String name);

    public Employee findById(int id);

    public Employee addEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public Employee deleteEmployee(int id);
}

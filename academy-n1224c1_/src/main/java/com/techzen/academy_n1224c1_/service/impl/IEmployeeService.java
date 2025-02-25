package com.techzen.academy_n1224c1_.service.impl;

import com.techzen.academy_n1224c1_.model.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> getEmployee(String name);
    public Employee findById(int id);
    public Employee addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public Employee deleteEmployee(int id);
}

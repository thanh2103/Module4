package com.techzen.academy_n1224c1_.controller;

import com.techzen.academy_n1224c1_.dto.ApiResponse;
import com.techzen.academy_n1224c1_.exception.ApiException;
import com.techzen.academy_n1224c1_.exception.Errorcode;
import com.techzen.academy_n1224c1_.model.Employee;
import com.techzen.academy_n1224c1_.model.Student;
import com.techzen.academy_n1224c1_.service.EmployeeService;
import com.techzen.academy_n1224c1_.service.impl.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor//
@Scope("singleton")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    IEmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployee(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(ApiResponse.<List<Employee>>builder()
                .data(employeeService.getEmployee(name))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> findById(@RequestParam(defaultValue = "") int id) {
        return ResponseEntity.ok(ApiResponse.<Employee>builder()
                .data(employeeService.findById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(ApiResponse.<Employee>builder()
                .data(employeeService.addEmployee(employee))
                .build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@RequestBody Employee employee, @PathVariable("id") int id) {
        return ResponseEntity.ok(ApiResponse.<Employee>builder()
                .data(employeeService.updateEmployee(employee))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> deleteEmployee(@PathVariable("id") int id) {
        return ResponseEntity.ok(ApiResponse.<Employee>builder()
                .data(employeeService.deleteEmployee(id))
                .build());
    }

}

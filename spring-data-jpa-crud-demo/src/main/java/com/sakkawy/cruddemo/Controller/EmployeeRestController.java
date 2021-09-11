package com.sakkawy.cruddemo.Controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import com.sakkawy.cruddemo.Entity.Employee;
import com.sakkawy.cruddemo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // get employee by id
    @GetMapping("/employees/{empId}")
    public Employee getEmployee(@PathVariable int empId) {
        Employee employee =  employeeService.findById(empId);
        if(employee == null){
            throw new RuntimeException("Employee id not found - "+ empId);
        }
        return employee;
    }

    // add mapping for post /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // also just in case they pass an id in Json ... set id = 0 
        // this if force a save of new item ... instead of update
        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }

    // add mapping for put /employee - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    // delete employee
    @DeleteMapping("/employees/{empId}")
    public String deleteEmployee(@PathVariable int empId) {
        Employee employee = employeeService.findById(empId);
        if(employee == null){
            throw new RuntimeException("Employee id not found - "+ empId);
        }
        employeeService.deleteById(empId);
        return "Deleted employee id - "+ empId;
    }

}

package com.sakkawy.cruddemo.DAO;

import java.util.List;

import com.sakkawy.cruddemo.Entity.Employee;

public interface EmployeeDAO {
    public List<Employee> findAll();

    public Employee findById(int id);

    public void save (Employee employee);
    
    public void deleteById(int id);
}

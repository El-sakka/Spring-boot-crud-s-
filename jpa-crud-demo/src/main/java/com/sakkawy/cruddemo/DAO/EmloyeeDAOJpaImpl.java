package com.sakkawy.cruddemo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sakkawy.cruddemo.Entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmloyeeDAOJpaImpl implements EmployeeDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        Query query = entityManager.createQuery("from Employee");

        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Employee dEmployee = entityManager.merge(employee);
        employee.setId(dEmployee.getId());
    }

    @Override
    public void deleteById(int id) {
       Query query = entityManager.createQuery("delete from Employee where id=:empId");
       query.setParameter("empId", id);
       query.executeUpdate();

    }
    
}

package org.spring_hibernate.dao;

import org.spring_hibernate.model.Employee;

import java.util.List;

public interface EmployeeDao {
    void saveEmloyee(Employee employee);
    List<Employee> findAllEmployees();
    void deleteEmployeeBySsn(String ssn);
    Employee findOneBySsn(String ssn);
    void updateEmployee(Employee employee);
}

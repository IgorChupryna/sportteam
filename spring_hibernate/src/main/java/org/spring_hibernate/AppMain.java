package org.spring_hibernate;

import org.joda.time.LocalDate;
import org.spring_hibernate.config.AppConfig;
import org.spring_hibernate.model.Employee;

import org.spring_hibernate.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by aleksey on 26.10.17.
 */
public class AppMain {

    public static void main(String[] args) {

        AbstractApplicationContext abstractApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        EmployeeService employeeService = (EmployeeService) abstractApplicationContext.getBean("employeeService");

//        Flower flower = new Flower("freedom", 60);

           /*
         * Create Employee1
         */
        Employee employee1 = new Employee();
        employee1.setName("Han Yenn");
        employee1.setJoiningDate(new LocalDate(2010, 10, 10));
        employee1.setSalary(new BigDecimal(10000));
        employee1.setSsn("ssn00000001");

        /*
         * Create Employee2
         */
        Employee employee2 = new Employee();
        employee2.setName("Dan Thomas");
        employee2.setJoiningDate(new LocalDate(2012, 11, 11));
        employee2.setSalary(new BigDecimal(20000));
        employee2.setSsn("ssn00000002");

        /*
         * Persist both Employees
         */
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);

        /*
         * Get all employees list from database
         */
        List<Employee> employees = employeeService.findAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        /*
         * delete an employee
         */
        employeeService.deleteEmployeeBySsn("ssn00000002");

        /*
         * update an employee
         */

        Employee employee = employeeService.findOneBySsn("ssn00000001");
        employee.setSalary(new BigDecimal(50000));
        employeeService.updateEmployee(employee);

        /*
         * Get all employees list from database
         */
        List<Employee> employeeList = employeeService.findAllEmployees();
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        abstractApplicationContext.close();
    }
}
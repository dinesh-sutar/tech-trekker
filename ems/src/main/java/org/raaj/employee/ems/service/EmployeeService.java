package org.raaj.employee.ems.service;

import java.util.List;

import org.raaj.employee.ems.model.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee updateEmployee(String id);

    void deleteEmployee(String id);

    List<Employee> getListOfEmployee();

    Employee getEmployeeById(String id);
}

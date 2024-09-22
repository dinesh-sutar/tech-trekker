package org.raaj.employee.ems.service.impl;

import java.util.List;

import org.raaj.employee.ems.model.Employee;
import org.raaj.employee.ems.repository.EmployeeRepository;
import org.raaj.employee.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployee'");
    }

    @Override
    public void deleteEmployee(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }

    @Override
    public List<Employee> getListOfEmployee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListOfEmployee'");
    }

    @Override
    public Employee getEmployeeById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmployeeById'");
    }

}

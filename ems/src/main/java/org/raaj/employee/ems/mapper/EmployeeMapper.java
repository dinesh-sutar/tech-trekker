package org.raaj.employee.ems.mapper;

import org.raaj.employee.ems.dto.EmployeeDTO;
import org.raaj.employee.ems.model.Employee;
import org.springframework.beans.BeanUtils;

public final class EmployeeMapper {
    private EmployeeMapper() {
    }

    public static Employee convertToEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }
}

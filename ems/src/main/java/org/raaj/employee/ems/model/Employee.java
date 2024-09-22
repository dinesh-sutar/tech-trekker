package org.raaj.employee.ems.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String employeeId;
    private String employeeName;
    private long employeeNumber;
}

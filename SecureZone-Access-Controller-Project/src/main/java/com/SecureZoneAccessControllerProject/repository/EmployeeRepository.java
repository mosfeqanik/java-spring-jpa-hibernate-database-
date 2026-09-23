package com.SecureZoneAccessControllerProject.repository;

import com.SecureZoneAccessControllerProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
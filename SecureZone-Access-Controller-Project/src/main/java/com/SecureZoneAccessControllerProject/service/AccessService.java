package com.SecureZoneAccessControllerProject.service;

import com.SecureZoneAccessControllerProject.model.Employee;
import com.SecureZoneAccessControllerProject.model.SecureZone;
import com.SecureZoneAccessControllerProject.repository.EmployeeRepository;
import com.SecureZoneAccessControllerProject.repository.SecureZoneRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Mark this class as a Spring Service component
@Service
public class AccessService {

    // Inject repositories
    private final EmployeeRepository employeeRepository;
    private final SecureZoneRepository secureZoneRepository;

    // Constructor injection
    public AccessService(
            EmployeeRepository employeeRepository,
            SecureZoneRepository secureZoneRepository) {

        this.employeeRepository = employeeRepository;
        this.secureZoneRepository = secureZoneRepository;
    }

    public Employee createEmployee(Employee employee) {
        // Save employee
        return employeeRepository.save(employee);
    }

    public SecureZone createZone(SecureZone zone) {
        // Save secure zone
        return secureZoneRepository.save(zone);
    }

    public Employee grantZoneAccess(Long employeeId, Long zoneId) {

        // 1. Fetch Employee
        Optional<Employee> optionalEmployee =
                employeeRepository.findById(employeeId);

        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }

        Employee employee = optionalEmployee.get();

        // 2. Fetch Secure Zone
        Optional<SecureZone> optionalZone =
                secureZoneRepository.findById(zoneId);

        if (optionalZone.isEmpty()) {
            throw new RuntimeException("Zone not found");
        }

        SecureZone zone = optionalZone.get();

        // 3. Grant access
        employee.addZone(zone);

        // Save updated employee
        return employeeRepository.save(employee);
    }

    public void revokeEmployee(Long employeeId) {
        // Delete employee by ID
        employeeRepository.deleteById(employeeId);
    }
}
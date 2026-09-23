package com.SecureZoneAccessControllerProject.controller;

import com.SecureZoneAccessControllerProject.model.Employee;
import com.SecureZoneAccessControllerProject.model.SecureZone;
import com.SecureZoneAccessControllerProject.service.AccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Mark this class as a Spring REST Controller
@RestController
// Set the base routing path
@RequestMapping("/api")
public class AccessController {

    // Inject AccessService
    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    // 1. Create Employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        return ResponseEntity.ok(
                accessService.createEmployee(employee)
        );
    }

    // 2. Create Secure Zone
    @PostMapping("/zones")
    public ResponseEntity<SecureZone> addZone(@RequestBody SecureZone zone) {

        return ResponseEntity.ok(
                accessService.createZone(zone)
        );
    }

    // 3. Give Employee access to Secure Zone
    @PostMapping("/employees/{employeeId}/zones/{zoneId}")
    public ResponseEntity<Employee> grantAccess(
            @PathVariable Long employeeId,
            @PathVariable Long zoneId) {

        return ResponseEntity.ok(
                accessService.grantZoneAccess(employeeId, zoneId)
        );
    }

    // 4. Delete Employee
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> removeEmployee(
            @PathVariable Long employeeId) {

        accessService.revokeEmployee(employeeId);

        return ResponseEntity.ok(
                "Employee, their RFID Card, and zone mappings have been securely removed."
        );
    }
}
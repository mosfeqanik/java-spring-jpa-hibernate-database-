package com.example.demo.controller;

import com.example.demo.dto.PatientAdmissionRequest;
import com.example.demo.model.Patient;
import com.example.demo.model.PatientStatus;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Mark this class as a REST Controller
@RestController

// Map all requests to start with "/api"
@RequestMapping("/api")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // POST /api/admit
    @PostMapping("/admit")
    public ResponseEntity<Patient> admitPatient(
            @Valid @RequestBody PatientAdmissionRequest request) {

        // Call service
        Patient patient = service.admitPatient(request);

        // Return 201 CREATED
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    // GET /api/critical
    @GetMapping("/critical")
    public ResponseEntity<List<Patient>> getCriticalPatients() {

        // Return 200 OK
        return ResponseEntity.ok(service.getCriticalPatients());
    }

    // GET /api/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {

        // Return 200 OK
        return ResponseEntity.ok(service.getPatientRecord(id));
    }

    // GET /api/alerts/high-bp?sys=150&dia=100
    @GetMapping("/alerts/high-bp")
    public ResponseEntity<List<Patient>> getHighBpPatients(
            @RequestParam int sys,
            @RequestParam int dia) {

        // Return 200 OK
        return ResponseEntity.ok(
                service.getPatientsWithHighVitals(sys, dia)
        );
    }

    // PUT /api/{id}/status?status=CRITICAL
    @PutMapping("/{id}/status")
    public ResponseEntity<Patient> updateStatus(
            @PathVariable Long id,
            @RequestParam PatientStatus status) {

        // Update patient status
        Patient updatedPatient =
                service.updatePatientStatus(id, status);

        return ResponseEntity.ok(updatedPatient);
    }

    // DELETE /api/{id}/discharge
    @DeleteMapping("/{id}/discharge")
    public ResponseEntity<Void> dischargePatient(
            @PathVariable Long id) {

        // Delete patient
        service.dischargePatient(id);

        // Return 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }
}
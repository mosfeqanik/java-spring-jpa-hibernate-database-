



package com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.service;

import com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.dto.PatientAdmissionRequest;
import com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.model.Patient;
import com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.model.PatientStatus;
import com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.List;

// Mark this class as a Spring Service component
@Service
public class PatientService {

    // Constructor injection
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient admitPatient(PatientAdmissionRequest request) {
        Patient patient = new Patient();

        // Map DTO → Entity
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setSystolicBp(request.getSystolicBp());
        patient.setDiastolicBp(request.getDiastolicBp());

        // Auto-trigger critical flag logic
        if (request.getSystolicBp() >= 180 ||
                request.getDiastolicBp() >= 120) {

            patient.setStatus(PatientStatus.CRITICAL);

        } else if (request.getSystolicBp() >= 140 ||
                request.getDiastolicBp() >= 90) {

            patient.setStatus(PatientStatus.OBSERVATION);

        } else {

            patient.setStatus(PatientStatus.NORMAL);
        }

        // Save and return
        return patientRepository.save(patient);
    }

    public List<Patient> getCriticalPatients() {
        return patientRepository.findByStatus(PatientStatus.CRITICAL);
    }

    public Patient getPatientRecord(Long id) {

        // Find patient by ID
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        // Check if found
        if (optionalPatient.isPresent()) {

            // Extract Patient from Optional
            return optionalPatient.get();

        } else {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Patient with ID " + id + " not found."
            );
        }
    }

    // Fetch patients based on custom JPQL query
    public List<Patient> getPatientsWithHighVitals(
            int sysLimit,
            int diaLimit) {

        return patientRepository.findPatientsWithHighBp(
                sysLimit,
                diaLimit
        );
    }

    // Manually update patient's status
    public Patient updatePatientStatus(
            Long id,
            PatientStatus newStatus) {

        // Fetch patient
        Patient patient = getPatientRecord(id);

        // Update status
        patient.setStatus(newStatus);

        // Save updated record
        return patientRepository.save(patient);
    }

    // Discharge a patient
    public void dischargePatient(Long id) {

        // Fetch patient first
        Patient patient = getPatientRecord(id);

        // Delete patient
        patientRepository.delete(patient);
    }
}
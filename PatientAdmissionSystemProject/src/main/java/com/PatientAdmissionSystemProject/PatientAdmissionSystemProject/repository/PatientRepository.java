package com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.repository;

import com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.model.Patient;
import com.PatientAdmissionSystemProject.PatientAdmissionSystemProject.model.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

// Mark as Spring Data Repository
@Repository
// Entity = Patient, Primary Key = Long
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Derived query: find patients by status
    List<Patient> findByStatus(PatientStatus status);

    // Custom JPQL query
    @Query("SELECT p FROM Patient p WHERE p.systolicBp > ?1 OR p.diastolicBp > ?2")
    List<Patient> findPatientsWithHighBp(int systolicLimit, int diastolicLimit);
}
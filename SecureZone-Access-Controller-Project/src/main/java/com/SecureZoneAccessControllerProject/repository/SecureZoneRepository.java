package com.SecureZoneAccessControllerProject.repository;

import com.SecureZoneAccessControllerProject.model.SecureZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecureZoneRepository extends JpaRepository<SecureZone, Long> {
    
}
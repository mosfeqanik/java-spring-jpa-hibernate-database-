package com.SecureZoneAccessControllerProject.repository;

import com.SecureZoneAccessControllerProject.model.RfidCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RfidCardRepository extends JpaRepository<RfidCard, Long> {

}
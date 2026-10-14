package com.CampusPlacementPortalProject.repository;

import com.CampusPlacementPortalProject.model.RecruitmentDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// TODO: Mark this interface as a Spring Data Repository component
@Repository
// TODO: Extend the correct interface to inherit standard CRUD database operations
// Hint: Pass in the Entity class name and the data type of its Primary Key (Long)
public interface RecruitmentDriveRepository extends JpaRepository<RecruitmentDrive, Long> {


}



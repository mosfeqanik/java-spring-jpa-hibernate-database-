package com.CampusPlacementPortalProject.controller;

import com.CampusPlacementPortalProject.model.RecruitmentDrive;
import com.CampusPlacementPortalProject.repository.RecruitmentDriveRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Mark this class as a REST Controller so Spring knows it handles HTTP requests
@RestController
// TODO: Set the base routing path for all endpoints in this controller to "/api"
@RequestMapping("/api")
public class PlacementController {

    // TODO: Inject the RecruitmentDriveRepository via Constructor Injection
    private final RecruitmentDriveRepository recruitmentDriveRepository;

    public PlacementController(RecruitmentDriveRepository recruitmentDriveRepository) {
        this.recruitmentDriveRepository = recruitmentDriveRepository;
    }

    // PUBLIC VIEW ENDPOINT (Accessible by Students & Officers):

    // TODO: Map this method to handle HTTP GET requests for the "/view" path
    @GetMapping("/view")
    public List<RecruitmentDrive> getAllDrives() {
        // TODO: Use the repository to fetch all drives from the H2 database
        return recruitmentDriveRepository.findAll();
    }

    // SECURE MANAGE ENDPOINTS (Accessible ONLY by Officers):

    // TODO: Map this method to handle HTTP POST requests for the "/manage/add" path
    @PostMapping("/manage/add")
    // TODO: Tell Spring to extract the 'drive' object from the incoming JSON request body
    public RecruitmentDrive addDrive(@RequestBody RecruitmentDrive drive) {
        // TODO: Use the repository to save the new drive to the H2 database
        return recruitmentDriveRepository.save(drive);
    }

    // TODO: Map this method to handle HTTP DELETE requests to remove a specific drive by ID
    // Endpoint: DELETE /api/manage/remove/{id}
    @DeleteMapping("/manage/remove/{id}")
    // TODO: Tell Spring to extract the 'id' variable directly from the URL path
    public String removeDrive(@PathVariable Long id) {

        // 1. Check if the drive actually exists in the database before trying to delete
        if (!recruitmentDriveRepository.existsById(id)) {
            // If not found, throw an exception or return an error message
            throw new RuntimeException("Drive not found");
        }

        // 2. TODO: Use the repository to delete the drive by its ID
        recruitmentDriveRepository.deleteById(id);

        return "Drive removed successfully!";
    }
}



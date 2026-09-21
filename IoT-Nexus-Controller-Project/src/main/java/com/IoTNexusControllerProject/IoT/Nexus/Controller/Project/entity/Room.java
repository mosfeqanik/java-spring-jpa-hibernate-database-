package com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

// TODO: Mark this class as a JPA Entity
@Entity
@Getter 
@Setter
public class Room {

    // TODO: Define the Primary Key 'id'
    @Id
    // TODO: Set the generation strategy to IDENTITY (Auto-Increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Standard fields
    private String name;  // e.g., "Living Room"
    private String floor; // e.g., "Ground Floor"

    // RELATIONSHIP MAPPING SECTION:

    // TODO: Define the relationship: One Room has Many Devices
    // Challenge 1: Use 'mappedBy' to tell Hibernate that the 'Device' class owns the relationship.
    //              (Look at the field name in the Device class!)
    // Challenge 2: Configure 'cascade' to propagate ALL operations.
    //              (We want to automatically DELETE devices when a Room is deleted, 
    //               AND automatically SAVE devices when a Room is saved.)
    //              Hint: Use the type that covers EVERYTHING (Persist, Merge, Remove, etc.)
    @OneToMany(
        mappedBy = "room",
        cascade = CascadeType.ALL
    )
    private List<Device> devices;
}


package com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.repository;

import com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Define this as an Interface (not a class)
// TODO: Extend the 'JpaRepository' to inherit standard CRUD operations (Save, Find, Delete)
//
// Generics Challenge: 
// 1. First Generic: Which Entity class is this repository managing?
// 2. Second Generic: What is the data type of the Primary Key (@Id) in that Entity?
public interface DeviceRepository extends JpaRepository<Device,Long> {

}



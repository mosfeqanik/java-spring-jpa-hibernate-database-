package com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.service;

import com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.entity.Device;
import com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.entity.Room;
import com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.repository.DeviceRepository;
import com.IoTNexusControllerProject.IoT.Nexus.Controller.Project.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IoTService {

    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;

    public IoTService(RoomRepository roomRepository, DeviceRepository deviceRepository) {
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
    }

    // 1. Create a Room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // 2. Get All Rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // 3. Add a Device to a Specific Room
    public Device addDevice(Long roomId, Device device) {

        // Find the Room by ID
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        // Check if Room exists
        if (roomOptional.isPresent()) {

            // Get the actual Room object
            Room foundRoom = roomOptional.get();

            // Link Device to Room
            device.setRoom(foundRoom);

            // Save Device
            return deviceRepository.save(device);

        } else {
            throw new RuntimeException("Room not found with id: " + roomId);
        }
    }

    // 4. Delete a Room
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    // 5. Delete a Device
    public void deleteDevice(Long deviceId) {

        // Check if device exists
        if (deviceRepository.existsById(deviceId)) {

            // Delete device
            deviceRepository.deleteById(deviceId);

        } else {
            throw new RuntimeException("Device not found with id: " + deviceId);
        }
    }
}
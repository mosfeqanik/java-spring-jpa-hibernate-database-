package com.example.demo.controller;

import com.example.demo.entity.Device;
import com.example.demo.entity.Room;
import com.example.demo.service.IoTService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IoTController {

    // Dependency
    private final IoTService iotService;

    // Constructor Injection
    public IoTController(IoTService iotService) {
        this.iotService = iotService;
    }

    // 1. Create a Room
    @PostMapping("/rooms")
    public Room createRoom(@RequestBody Room room) {
        return iotService.createRoom(room);
    }

    // 2. Get all Rooms
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return iotService.getAllRooms();
    }

    // 3. Add a Device to a specific Room
    @PostMapping("/rooms/{roomId}/devices")
    public Device addDeviceToRoom(
            @PathVariable Long roomId,
            @RequestBody Device device) {

        return iotService.addDevice(roomId, device);
    }

    // 4. Delete a Room
    @DeleteMapping("/rooms/{id}")
    public String deleteRoom(@PathVariable Long id) {

        iotService.deleteRoom(id);

        return "Room deleted successfully.";
    }

    // 5. Delete a Device
    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable Long id) {

        iotService.deleteDevice(id);

        return "Device deleted successfully.";
    }
}
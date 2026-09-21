package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Define this as an Interface (not a class)
// TODO: Extend the 'JpaRepository' to enable database operations
//
// Generics Challenge:
// 1. First Generic: Which Entity class is this repository managing?
// 2. Second Generic: What is the data type of the Primary Key (@Id) in that Entity?
public interface RoomRepository extends JpaRepository<Room,Long> {
}



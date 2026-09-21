package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

// TODO: Mark this class as a JPA Entity so it maps to a database table
@Entity
@Getter 
@Setter
public class Device {

    // TODO: Mark this field as the Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Standard fields for the device
    private String name;
    private String status;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;
}



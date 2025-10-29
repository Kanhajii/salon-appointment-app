package com.SalonAppoinment.salonAppoinment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "salons")
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ownerName;
    private String address;
    private String city;
    private String contactNumber;

    public Salon() {}

    public Salon(String name, String ownerName, String address, String city, String contactNumber) {
        this.name = name;
        this.ownerName = ownerName;
        this.address = address;
        this.city = city;
        this.contactNumber = contactNumber;
    }

    // Getters and setters...
}


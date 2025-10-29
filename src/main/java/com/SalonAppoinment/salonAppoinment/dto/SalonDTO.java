package com.SalonAppoinment.salonAppoinment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalonDTO {
    private String name;
    private String ownerName;
    private String address;
    private String city;
    private String contactNumber;

    public SalonDTO(String name, String ownerName, String address, String city, String contactNumber) {
        this.name = name;
        this.ownerName = ownerName;
        this.address = address;
        this.city = city;
        this.contactNumber = contactNumber;
    }
}

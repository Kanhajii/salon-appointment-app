package com.SalonAppoinment.salonAppoinment.service;


import com.SalonAppoinment.salonAppoinment.dto.SalonDTO;
import com.SalonAppoinment.salonAppoinment.entity.Salon;
import com.SalonAppoinment.salonAppoinment.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalonService {

    @Autowired
    private SalonRepository salonRepository;

    // ✅ Create salon
    public SalonDTO addSalon(SalonDTO salonDTO) {
        Salon salon = new Salon(
                salonDTO.getName(),
                salonDTO.getOwnerName(),
                salonDTO.getAddress(),
                salonDTO.getCity(),
                salonDTO.getContactNumber()
        );

        salon = salonRepository.save(salon);

        return new SalonDTO(
                salon.getId(),
                salon.getName(),
                salon.getOwnerName(),
                salon.getAddress(),
                salon.getCity(),
                salon.getContactNumber()
        );
    }

    // ✅ Get all salons
    public List<SalonDTO> getAllSalons() {
        return salonRepository.findAll()
                .stream()
                .map(s -> new SalonDTO(
                        s.getId(),
                        s.getName(),
                        s.getOwnerName(),
                        s.getAddress(),
                        s.getCity(),
                        s.getContactNumber()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Get salons by owner (for owner dashboard)
    public List<SalonDTO> getSalonsByOwner(String ownerName) {
        List<Salon> salons = salonRepository.findByOwnerName(ownerName);
        return salons.stream()
                .map(s -> new SalonDTO(s.getId(),s.getName(),s.getOwnerName(),s.getAddress(), s.getCity(), s.getContactNumber()))
                .collect(Collectors.toList());
    }

}

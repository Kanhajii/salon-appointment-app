package com.SalonAppoinment.salonAppoinment.controller;

import com.SalonAppoinment.salonAppoinment.dto.SalonDTO;
import com.SalonAppoinment.salonAppoinment.entity.Salon;
import com.SalonAppoinment.salonAppoinment.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
public class SalonController {

    @Autowired
    private SalonService salonService;

    // ✅ API to add a new salon
    @PostMapping
    public SalonDTO addSalon(@RequestBody SalonDTO salonDTO) {
        return salonService.addSalon(salonDTO);
    }

    // ✅ GET - all salons (for customers)
    @GetMapping
    public List<SalonDTO> getAllSalons() {
        return salonService.getAllSalons();
    }

    // ✅ GET - salons by owner
    @GetMapping("/owner/{ownerName}")
    public List<SalonDTO> getSalonsByOwner(@PathVariable String ownerName) {
        return salonService.getSalonsByOwner(ownerName);
    }

}

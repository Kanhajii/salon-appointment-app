package com.SalonAppoinment.salonAppoinment.controller;

import com.SalonAppoinment.salonAppoinment.dto.SalonDTO;
import com.SalonAppoinment.salonAppoinment.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

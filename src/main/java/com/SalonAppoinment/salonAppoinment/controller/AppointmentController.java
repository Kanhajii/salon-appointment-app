package com.SalonAppoinment.salonAppoinment.controller;

import com.SalonAppoinment.salonAppoinment.dto.AppointmentDTO;
import com.SalonAppoinment.salonAppoinment.dto.AppointmentStatusUpdateDTO;
import com.SalonAppoinment.salonAppoinment.entity.Appointment;
import com.SalonAppoinment.salonAppoinment.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentDTO dto) {
        try {
            Appointment appointment = appointmentService.bookAppointment(dto);
            return ResponseEntity.ok(appointment); // ✅ returns JSON object
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping("/customer/{username}")
    public List<Appointment> getCustomerAppointments(@PathVariable String username) {
        return appointmentService.getAppointmentsByCustomer(username);
    }

    @GetMapping("/salon/{salonName}")
    public List<Appointment> getSalonAppointments(@PathVariable String salonName) {
        return appointmentService.getAppointmentsBySalon(salonName);
    }

    @PutMapping("/update-status")
    public ResponseEntity<String> updateAppointmentStatus(@RequestBody AppointmentStatusUpdateDTO dto) {
        appointmentService.updateAppointmentStatus(dto);
        return ResponseEntity.ok("Appointment status updated to " + dto.getStatus());
    }

}


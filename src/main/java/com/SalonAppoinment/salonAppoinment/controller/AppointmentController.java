package com.SalonAppoinment.salonAppoinment.controller;

import com.SalonAppoinment.salonAppoinment.dto.AppointmentDTO;
import com.SalonAppoinment.salonAppoinment.entity.Appointment;
import com.SalonAppoinment.salonAppoinment.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody AppointmentDTO dto) {
        return appointmentService.bookAppointment(dto);
    }

    @GetMapping("/customer/{username}")
    public List<Appointment> getCustomerAppointments(@PathVariable String username) {
        return appointmentService.getAppointmentsByCustomer(username);
    }

    @GetMapping("/salon/{salonName}")
    public List<Appointment> getSalonAppointments(@PathVariable String salonName) {
        return appointmentService.getAppointmentsBySalon(salonName);
    }
}


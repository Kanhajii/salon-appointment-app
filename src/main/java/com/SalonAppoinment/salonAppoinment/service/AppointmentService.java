package com.SalonAppoinment.salonAppoinment.service;

import com.SalonAppoinment.salonAppoinment.dto.AppointmentDTO;
import com.SalonAppoinment.salonAppoinment.entity.Appointment;
import com.SalonAppoinment.salonAppoinment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment bookAppointment(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setSalonId(dto.getSalonId());
        appointment.setSalonName(dto.getSalonName());
        appointment.setCustomerUsername(dto.getCustomerUsername());
        appointment.setCustomerName(dto.getCustomerName());
        appointment.setServiceName(dto.getServiceName());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus("BOOKED");

        return appointmentRepository.save(appointment);
    }


    public List<Appointment> getAppointmentsByCustomer(String username) {
        return appointmentRepository.findByCustomerUsername(username);
    }

    public List<Appointment> getAppointmentsBySalon(String salonName) {
        return appointmentRepository.findBySalonName(salonName);
    }
}


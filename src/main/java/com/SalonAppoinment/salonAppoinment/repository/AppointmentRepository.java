package com.SalonAppoinment.salonAppoinment.repository;

import com.SalonAppoinment.salonAppoinment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerUsername(String customerUsername);
    List<Appointment> findBySalonName(String salonName);
}


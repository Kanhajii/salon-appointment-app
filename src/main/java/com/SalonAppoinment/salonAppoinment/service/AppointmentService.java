package com.SalonAppoinment.salonAppoinment.service;

import com.SalonAppoinment.salonAppoinment.dto.AppointmentDTO;
import com.SalonAppoinment.salonAppoinment.dto.AppointmentStatusUpdateDTO;
import com.SalonAppoinment.salonAppoinment.entity.Appointment;
import com.SalonAppoinment.salonAppoinment.entity.Salon;
import com.SalonAppoinment.salonAppoinment.entity.User;
import com.SalonAppoinment.salonAppoinment.repository.AppointmentRepository;
import com.SalonAppoinment.salonAppoinment.repository.SalonRepository;
import com.SalonAppoinment.salonAppoinment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final SalonRepository salonRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              UserRepository userRepository,
                              EmailService emailService, SalonRepository salonRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.salonRepository = salonRepository;
    }

    // ✅ Book Appointment
    // ✅ Book Appointment + Send Email to Salon Owner
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

        // ✅ Set customer (user)
        User customer = userRepository.findByUsername(dto.getCustomerUsername()).orElse(null);
        appointment.setUser(customer);

        // ✅ Save appointment
        Appointment savedAppointment = appointmentRepository.save(appointment);

        try {
            // ✅ Step 1: Find salon owner name from salon table
            Salon salon = salonRepository.findById(dto.getSalonId())
                    .orElseThrow(() -> new RuntimeException("Salon not found with ID: " + dto.getSalonId()));

            // ✅ Step 2: Find owner email from user table (using ownerName)
            User owner = userRepository.findByUsername(salon.getOwnerName())
                    .orElseThrow(() -> new RuntimeException("Owner not found for salon: " + salon.getOwnerName()));

            if (owner.getEmail() != null && !owner.getEmail().isEmpty()) {
                // ✅ Step 3: Send notification email
                String subject = "📅 New Appointment Booked for Your Salon";
                String message = "Hello " + owner.getUsername() + ",\n\n" +
                        "A new appointment has been booked at your salon.\n\n" +
                        "Customer: " + dto.getCustomerName() + "\n" +
                        "Service: " + dto.getServiceName() + "\n" +
                        "Date: " + dto.getAppointmentDate() + "\n" +
                        "Time: " + dto.getAppointmentTime() + "\n\n" +
                        "Please review this appointment in your dashboard.\n\n" +
                        "Regards,\nSalon Appointment System 💇‍♂️";

                emailService.sendEmail(owner.getEmail(), subject, message);
                System.out.println("📧 Email sent to salon owner: " + owner.getEmail());
            } else {
                System.out.println("⚠ No email found for owner: " + salon.getOwnerName());
            }

        } catch (Exception e) {
            System.err.println("❌ Error sending email to owner: " + e.getMessage());
        }

        return savedAppointment;
    }


    // ✅ Fetch all appointments for customer
    public List<Appointment> getAppointmentsByCustomer(String username) {
        return appointmentRepository.findByCustomerUsername(username);
    }

    // ✅ Fetch all appointments for salon
    public List<Appointment> getAppointmentsBySalon(String salonName) {
        return appointmentRepository.findBySalonName(salonName);
    }

    // ✅ Update appointment status + send email
    public void updateAppointmentStatus(AppointmentStatusUpdateDTO dto) {
        Appointment appointment = appointmentRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(dto.getStatus().toUpperCase());
        appointmentRepository.save(appointment);

        // Prepare email details
        String subject = "Appointment " + dto.getStatus();
        String message;

        if ("CONFIRMED".equalsIgnoreCase(dto.getStatus())) {
            message = "Your appointment with " + appointment.getSalonName() +
                    " has been confirmed ✅.\n" +
                    "Date: " + appointment.getAppointmentDate() +
                    " | Time: " + appointment.getAppointmentTime();
        } else if ("REJECTED".equalsIgnoreCase(dto.getStatus())) {
            message = "Sorry, your appointment with " + appointment.getSalonName() +
                    " has been rejected ❌.";
        } else {
            message = "Your appointment status has been updated to " + dto.getStatus();
        }

        // ✅ Email send logic (check null user)
        if (appointment.getUser() != null && appointment.getUser().getEmail() != null) {
            emailService.sendEmail(appointment.getUser().getEmail(), subject, message);
        } else {
            System.out.println("⚠️ Email not sent: user email is missing for appointment ID " + dto.getId());
        }
    }
}

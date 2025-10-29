package com.SalonAppoinment.salonAppoinment.repository;

import com.SalonAppoinment.salonAppoinment.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepository extends JpaRepository<Salon, Long> {
}


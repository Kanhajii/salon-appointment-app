package com.SalonAppoinment.salonAppoinment.repository;

import com.SalonAppoinment.salonAppoinment.dto.SalonDTO;
import com.SalonAppoinment.salonAppoinment.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Long> {

    @Query("SELECT s FROM Salon s WHERE s.ownerName = :ownerName")
    List<Salon> findByOwnerName(@Param("ownerName") String ownerName);
}


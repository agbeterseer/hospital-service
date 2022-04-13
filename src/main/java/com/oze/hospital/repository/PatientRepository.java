package com.oze.hospital.repository;

import com.oze.hospital.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value ="SELECT * FROM patients WHERE last_visit_date >= current_date - INTERVAL '720' DAY" , nativeQuery = true)
    Page<Patient> getAllPatientUpToTwoYears(Pageable pageable);

    @Query("SELECT a FROM Patient a WHERE a.createdAt >= :startDate AND a.createdAt <= :endDate")
    Page<Patient> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

}

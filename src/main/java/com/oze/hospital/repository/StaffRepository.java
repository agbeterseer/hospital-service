package com.oze.hospital.repository;

import com.oze.hospital.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByUuid(UUID uuid);
}

package com.oze.hospital.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StaffResponse {
    private Long id;
    private UUID uuid;
    private String name;
    @JsonProperty("registration_date")
    private LocalDateTime registrationDate;
}

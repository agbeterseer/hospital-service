package com.oze.hospital.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class PatientResponse {
    private Long id;
    private String name;
    private int age;
    @JsonProperty("last_visit_date")
    private LocalDateTime lastVisitDate;
}

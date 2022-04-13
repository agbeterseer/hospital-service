package com.oze.hospital.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@Table(name = "patients")
public class Patient extends Model {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 225)
    private String name;

    @Column(length = 2)
    private int age;

    @JsonProperty("last_visit_date")
    private LocalDateTime lastVisitDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getAge() == patient.getAge() &&
                getId().equals(patient.getId()) &&
                getName().equals(patient.getName()) &&
                getLastVisitDate().equals(patient.getLastVisitDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getLastVisitDate());
    }
}

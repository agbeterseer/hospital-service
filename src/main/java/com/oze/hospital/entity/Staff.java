package com.oze.hospital.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Data
@Entity
@Table(name = "staffs")
public class Staff extends Model{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 225)
    private String name;

    @Type(type="uuid-char")
    @Column(unique = true, nullable = false, length = 36)
    private UUID uuid;

    @JsonProperty("registration_date")
    private LocalDateTime registrationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return Objects.equals(getId(), staff.getId()) &&
                Objects.equals(getName(), staff.getName()) &&
                Objects.equals(getUuid(), staff.getUuid()) &&
                Objects.equals(getRegistrationDate(), staff.getRegistrationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUuid(), getRegistrationDate());
    }
}

package com.oze.hospital.pojo.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateStaffProfile {
    private Long id;
    private String name;
}

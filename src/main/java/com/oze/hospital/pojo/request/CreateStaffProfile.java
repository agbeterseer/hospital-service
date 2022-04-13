package com.oze.hospital.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateStaffProfile {
    @NotBlank(message = "please provide your name")
    private String name;
}

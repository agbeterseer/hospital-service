package com.oze.hospital.controller;

import com.oze.hospital.exception.CustomException;
import com.oze.hospital.pojo.request.CreateStaffProfile;
import com.oze.hospital.pojo.request.UpdateStaffProfile;
import com.oze.hospital.pojo.response.ApiResponseBody;
import com.oze.hospital.pojo.response.StaffResponse;
import com.oze.hospital.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/staff")
@Validated
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @PostMapping("/add")
    ResponseEntity<ApiResponseBody<StaffResponse>> createStaffMem(@Valid @RequestBody CreateStaffProfile data) throws CustomException {
        return staffService.createStaffProfile(data);
    }

    @PutMapping("/update/{staffUuid}")
    ResponseEntity<ApiResponseBody<StaffResponse>> update(@Valid @RequestBody UpdateStaffProfile data, @PathVariable String staffUuid) throws CustomException {
        return staffService.updateStaffProfile(data, staffUuid);
    }

}

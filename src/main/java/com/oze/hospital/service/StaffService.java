package com.oze.hospital.service;

import com.oze.hospital.pojo.request.CreateStaffProfile;
import com.oze.hospital.pojo.request.UpdateStaffProfile;
import com.oze.hospital.pojo.response.ApiResponseBody;
import com.oze.hospital.pojo.response.StaffResponse;
import org.springframework.http.ResponseEntity;

public interface StaffService {
    ResponseEntity<ApiResponseBody<StaffResponse>> createStaffProfile(CreateStaffProfile request);
    ResponseEntity<ApiResponseBody<StaffResponse>> updateStaffProfile(UpdateStaffProfile request, String staffUuid);
}

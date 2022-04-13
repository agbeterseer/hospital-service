package com.oze.hospital.service;

import com.oze.hospital.pojo.request.DeletePojo;
import com.oze.hospital.pojo.request.DeleteProfileRequest;
import com.oze.hospital.pojo.response.ApiResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PatientService {

    ResponseEntity<ApiResponseBody<Map<String, Object>>> getAllPatientProfiles(int page, int size, String staffUuid);
    ResponseEntity<?> deleteProfile(DeleteProfileRequest data, int page, int size);
}

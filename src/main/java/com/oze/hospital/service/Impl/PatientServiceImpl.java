package com.oze.hospital.service.Impl;

import com.oze.hospital.entity.Patient;
import com.oze.hospital.entity.Staff;
import com.oze.hospital.exception.CustomException;
import com.oze.hospital.pojo.request.DeletePojo;
import com.oze.hospital.pojo.request.DeleteProfileRequest;
import com.oze.hospital.pojo.response.ApiResponseBody;
import com.oze.hospital.pojo.response.PatientResponse;
import com.oze.hospital.repository.PatientRepository;
import com.oze.hospital.repository.StaffRepository;
import com.oze.hospital.service.PatientService;
import static com.oze.hospital.util.Constant.*;
import com.oze.hospital.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.oze.hospital.util.Constant.SUCCESS_MESSAGE;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, StaffRepository staffRepository) {
        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public ResponseEntity<ApiResponseBody<Map<String, Object>>> getAllPatientProfiles(int page, int size, String staffUuid) {

        // get all patient profile up to 2years old
        ValidatorUtil validatorUtil = new ValidatorUtil();
        ApiResponseBody<String> validate = validatorUtil.validateStaffUuid(staffUuid);

        if (!validate.getStatus()){
            throw new CustomException(INVALID_UUID, HttpStatus.EXPECTATION_FAILED);
        }

        Pageable paging = PageRequest.of(page, size);
        Page<Patient> patientPage = patientRepository.getAllPatientUpToTwoYears(paging);
        List<Patient> patientList = patientPage.getContent();

        Map<String, Object> response = new HashMap<>();
        List<PatientResponse> patientResponseList = getPatientResponse(patientList);
        response.put("patients", patientResponseList);
        response.put("currentPage", patientPage.getNumber());
        response.put("totalItems", patientPage.getTotalElements());
        response.put("totalPages", patientPage.getTotalPages());

        return new ResponseEntity<>(new ApiResponseBody<>(response, SUCCESS_MESSAGE, true), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteProfile(DeleteProfileRequest data, int page, int size) {
        // validate staff uuid
        int count = 0;
        validate(data.getStaffUuid());

        Staff staff = staffRepository.findByUuid(UUID.fromString(data.getStaffUuid()));
        if (staff == null){
            throw new CustomException(NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        Pageable paging = PageRequest.of(page, size);
        Page<Patient> patientPage = patientRepository.findByCreatedAtBetween(data.getStartDate(), data.getEndDate(),paging);
        List<Patient> patientList = patientPage.getContent();
        DeletePojo deletePojo = new DeletePojo();
        List<String> nameList = new ArrayList<>();

            if (!(patientList.size() > 0)){
               return new ResponseEntity<>(new ApiResponseBody<>(null, DELETE_ERROR_MESSAGE, false), HttpStatus.NOT_FOUND);
            }
            for (Patient patient : patientList) {
                patientRepository.delete(patient);
                count ++;
                nameList.add(patient.getName());
            }

            deletePojo.setTotalPatientDeleted(count);
            deletePojo.setDateRange("From: " + data.getStartDate() + " to -" + data.getEndDate() );
            deletePojo.setName(nameList);
            deletePojo.setTotalDateRange(patientPage.getTotalElements());
            return new ResponseEntity<>(new ApiResponseBody<>(deletePojo, DELETE_SUCCESS_MESSAGE, true), HttpStatus.OK);

   }

    private List<PatientResponse> getPatientResponse(List<Patient> patientList){
        List<PatientResponse> patientResponseList = new ArrayList<>();
        for (Patient patient : patientList) {
            PatientResponse profileResponse = new PatientResponse();
            profileResponse.setId(patient.getId());
            profileResponse.setName(patient.getName());
            profileResponse.setAge(patient.getAge());
            profileResponse.setLastVisitDate(patient.getLastVisitDate());
            patientResponseList.add(profileResponse);
        }
        return patientResponseList;
    }

    private void validate(String staffUuid){
        ValidatorUtil validatorUtil = new ValidatorUtil();
        ApiResponseBody<String> validate = validatorUtil.validateStaffUuid(staffUuid);

        if (!validate.getStatus()){
            throw new CustomException(INVALID_UUID, HttpStatus.EXPECTATION_FAILED);
        }
    }

}

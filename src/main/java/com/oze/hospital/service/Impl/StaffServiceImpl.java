package com.oze.hospital.service.Impl;

import com.oze.hospital.entity.Staff;
import com.oze.hospital.exception.CustomException;
import com.oze.hospital.pojo.request.CreateStaffProfile;
import com.oze.hospital.pojo.request.UpdateStaffProfile;
import com.oze.hospital.pojo.response.ApiResponseBody;
import com.oze.hospital.pojo.response.StaffResponse;
import com.oze.hospital.repository.StaffRepository;
import com.oze.hospital.service.StaffService;
import com.oze.hospital.util.Constant;
import com.oze.hospital.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.oze.hospital.util.Constant.ERROR_MESSAGE;
import static com.oze.hospital.util.Constant.SUCCESS_MESSAGE;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public ResponseEntity<ApiResponseBody<StaffResponse>> createStaffProfile(CreateStaffProfile request) {
        try {

            Staff staff = new Staff();
            staff.setUuid(UUID.randomUUID());
            staff.setName(request.getName());
            staff.setRegistrationDate(LocalDateTime.now());
            staff.setCreatedAt(LocalDateTime.now());

            StaffResponse response = getProfileResponse(staffRepository.save(staff));

            return new ResponseEntity<>(new ApiResponseBody<>(response, SUCCESS_MESSAGE, true), HttpStatus.CREATED);

        }catch (Exception e){
            throw new CustomException(ERROR_MESSAGE, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @Override
    public ResponseEntity<ApiResponseBody<StaffResponse>> updateStaffProfile(UpdateStaffProfile request, String staffUuid) {

        //validate staff Uuid
        ValidatorUtil validatorUtil = new ValidatorUtil();
        ApiResponseBody<String> validate = validatorUtil.validateStaffUuid(staffUuid);

        if (!validate.getStatus()){
            throw new CustomException(Constant.INVALID_UUID, HttpStatus.EXPECTATION_FAILED);
        }
        try {

            Staff profile = staffRepository.findByUuid(UUID.fromString(staffUuid));
            profile.setName(request.getName());

            StaffResponse response = getProfileResponse(staffRepository.save(profile));

            return new ResponseEntity<>(new ApiResponseBody<>(response, SUCCESS_MESSAGE, true), HttpStatus.CREATED);

        }catch (Exception e){
            throw new CustomException(ERROR_MESSAGE, HttpStatus.EXPECTATION_FAILED);
        }

    }

    private StaffResponse getProfileResponse(Staff staff){
        StaffResponse profileResponse = new StaffResponse();
        profileResponse.setId(staff.getId());
        profileResponse.setUuid(staff.getUuid());
        profileResponse.setRegistrationDate(staff.getRegistrationDate());
        profileResponse.setName(staff.getName());
        return profileResponse;
    }

}

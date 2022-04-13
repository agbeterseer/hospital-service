package com.oze.hospital.service.Impl;

import com.oze.hospital.entity.Patient;
import com.oze.hospital.exception.CustomException;
import com.oze.hospital.pojo.response.ApiResponseBody;
import com.oze.hospital.repository.PatientRepository;
import com.oze.hospital.service.CSVService;
import com.oze.hospital.util.CSVHelper;
import com.oze.hospital.util.Constant;
import com.oze.hospital.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;

@Service
public class CSVServiceImpl implements CSVService {

    private final PatientRepository patientRepository;

    @Autowired
    public CSVServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public ByteArrayInputStream load(String staffID, String patientID) throws CustomException {
        // validate staff uuid
        ValidatorUtil validatorUtil = new ValidatorUtil();
        ApiResponseBody<String> validate = validatorUtil.validateStaffUuid(staffID);

        if (!validate.getStatus()){
            throw new CustomException(Constant.INVALID_UUID, HttpStatus.NOT_FOUND);
        }
        try {
            Patient profile = patientRepository.findById(Long.parseLong(patientID)).orElseThrow(()-> new EntityNotFoundException("NOT FOUND"));
            return CSVHelper.profilesToCSV(profile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }



}

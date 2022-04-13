package com.oze.hospital.service;

import com.oze.hospital.exception.CustomException;

import java.io.ByteArrayInputStream;

public interface CSVService {
    ByteArrayInputStream load(String staffId, String patientID) throws CustomException;
}

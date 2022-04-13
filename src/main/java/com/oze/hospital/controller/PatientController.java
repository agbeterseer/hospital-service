package com.oze.hospital.controller;

import com.oze.hospital.exception.CustomException;
import com.oze.hospital.pojo.request.DeleteProfileRequest;
import com.oze.hospital.pojo.response.ApiResponseBody;
import com.oze.hospital.service.CSVService;
import com.oze.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patient")
@Validated
public class PatientController {

    private final CSVService csvService;
    private final PatientService patientService;

    @Autowired
    public PatientController(CSVService csvService, PatientService patientService) {
        this.csvService = csvService;
        this.patientService = patientService;
    }


    @GetMapping("/view-two-years-old-records/{staffUuid}")
    public ResponseEntity<ApiResponseBody<Map<String, Object>>> viewPatientsUpToTwoYearsOld(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @PathVariable String staffUuid) throws CustomException {
        return patientService.getAllPatientProfiles(page,size,staffUuid);
    }

    @GetMapping("/{patientID}/staff/{staffUuid}/download")
    public ResponseEntity<Resource> getFile(HttpServletResponse response, @PathVariable String staffUuid, @PathVariable String patientID) throws CustomException {
        response.setContentType("application/pdf");
        String filename = "patient.csv";

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=receipt_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        InputStreamResource file = new InputStreamResource(csvService.load(staffUuid, patientID));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<?> deletePatient(
            @RequestBody @Valid DeleteProfileRequest data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) throws CustomException {
        return patientService.deleteProfile(data,page,size);
    }

}

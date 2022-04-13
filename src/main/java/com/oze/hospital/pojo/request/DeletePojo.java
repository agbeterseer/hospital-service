package com.oze.hospital.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oze.hospital.entity.Patient;
import lombok.Data;

import java.util.List;

@Data
public class DeletePojo {
    private String dateRange;
    private List<String> name;
    @JsonProperty("total_patient_deleted")
    private long totalPatientDeleted;
    @JsonProperty("total_date_range")
    private long totalDateRange;

}

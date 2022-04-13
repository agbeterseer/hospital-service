package com.oze.hospital.pojo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class DeleteProfileRequest {

    private String staffUuid;

    @ApiModelProperty(example = "2021-07-01 13:09:33")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    private LocalDateTime startDate;

    @ApiModelProperty(example = "2021-07-01 13:09:33")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    private LocalDateTime endDate;
}

package com.project.userManagementTest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TestResultDto {

    private String testUrl;
    private String method;
    private Integer httpStatus;
    private Integer latencyInNanoSec;
}

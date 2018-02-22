package com.project.userManagementTest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class TestResultResponseDto implements Serializable{

    private final List<TestResultDto> testResult = new ArrayList<>();
}

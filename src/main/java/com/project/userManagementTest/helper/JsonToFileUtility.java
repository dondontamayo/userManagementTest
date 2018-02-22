package com.project.userManagementTest.helper;

import com.project.userManagementTest.exception.FailedRisksProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.userManagementTest.dto.TestResultResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class JsonToFileUtility {

    private static final String SEPARATOR = "_";
    private static final String FILE_EXTENSION = ".json";

    @Value("${folder.drive}")
    private String folderDrive;

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonToFileUtility(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void writeResponseToFile(final TestResultResponseDto testResultResponseDto) {

        final String fileName = LocalDateTime.now().toString() + SEPARATOR + FILE_EXTENSION;

        final File targetDestination = Paths.get(folderDrive, fileName).toFile();

        try {
            objectMapper.writeValue(targetDestination, testResultResponseDto);

        } catch (IOException error) {
            throw new FailedRisksProcessingException("Error writing file", error);
        }
    }
}
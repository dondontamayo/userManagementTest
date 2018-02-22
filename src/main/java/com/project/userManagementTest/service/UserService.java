package com.project.userManagementTest.service;

import com.project.userManagementTest.dto.TestResultDto;
import com.project.userManagementTest.dto.TestResultResponseDto;
import com.project.userManagementTest.dto.User;
import com.project.userManagementTest.helper.JsonToFileUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService implements CommandLineRunner{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JsonToFileUtility jsonToFileUtility;

    // in reality, these should be in the property yml file
    final String basePath ="http://localhost:8080";
    final String getAllUserPath = "/users?&page=0&size=3";
    final String getUserPath = "/users/";
    final String createUserPath = "/users";
    final String updateUserPath = "/users/";
    final String deleteUserPath = "/users/";
    final List<Integer> userIds = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {

        TestResultResponseDto testResultResponseDto = TestResultResponseDto
                .builder().build();



        // start adding results
        testResultResponseDto.getTestResult().add(testPutMethod(basePath + createUserPath,
                TestResultDto.builder().build()));
        testResultResponseDto.getTestResult().add(testPostMethod(basePath + updateUserPath + userIds.get(0).toString(),
                TestResultDto.builder().build()));
        testResultResponseDto.getTestResult().add(testGetUserMethod(basePath + getUserPath + userIds.get(0).toString(),
                TestResultDto.builder().build()));
        testResultResponseDto.getTestResult().add(testGetUserMethod(basePath + getAllUserPath,
                TestResultDto.builder().build()));
        testResultResponseDto.getTestResult().add(testDeleteMethod(basePath + deleteUserPath + userIds.get(0).toString(),
                TestResultDto.builder().build()));

        jsonToFileUtility.writeResponseToFile(testResultResponseDto);

    }
    private TestResultDto testPutMethod(String path, TestResultDto testResultDto) {

        Integer originalTime = LocalTime.now().getNano();

        testResultDto.setTestUrl(path);
        testResultDto.setMethod(HttpMethod.PUT.name());

        ResponseEntity<User> user1 = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<User>(User.builder()
                .name("name1")
                .password("pass1")
                .userName("userName1")
                .email("name1@yahoo.com")
                .build()), User.class);

        userIds.add(user1.getBody().getId());

        testResultDto.setHttpStatus(user1.getStatusCodeValue());
        testResultDto.setLatencyInNanoSec(LocalTime.now().getNano() - originalTime);
        return testResultDto;
    }

    private TestResultDto testPostMethod(String path, TestResultDto testResultDto) {

        Integer originalTime = LocalTime.now().getNano();

        testResultDto.setTestUrl(path);
        testResultDto.setMethod(HttpMethod.POST.name());

        ResponseEntity<User> user = restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<User>(User.builder()
                .name("name2")
                .password("pass2")
                .userName("userName2")
                .email("name2@yahoo.com")
                .build()), User.class);

        testResultDto.setHttpStatus(user.getStatusCodeValue());
        testResultDto.setLatencyInNanoSec(LocalTime.now().getNano() - originalTime);
        return testResultDto;
    }

    private TestResultDto testGetUserMethod(String path, TestResultDto testResultDto) {

        Integer originalTime = LocalTime.now().getNano();

        testResultDto.setTestUrl(path);
        testResultDto.setMethod(HttpMethod.GET.name());

        ResponseEntity<User> user = restTemplate.exchange(path, HttpMethod.GET, null, User.class);

        testResultDto.setHttpStatus(user.getStatusCodeValue());
        testResultDto.setLatencyInNanoSec(LocalTime.now().getNano() - originalTime);
        return testResultDto;
    }

    private TestResultDto testDeleteMethod(String path, TestResultDto testResultDto) {

        Integer originalTime = LocalTime.now().getNano();

        testResultDto.setTestUrl(path);
        testResultDto.setMethod(HttpMethod.DELETE.name());

        ResponseEntity<User> user = restTemplate.exchange(path, HttpMethod.DELETE, null, User.class);

        testResultDto.setHttpStatus(user.getStatusCodeValue());
        testResultDto.setLatencyInNanoSec(LocalTime.now().getNano() - originalTime);
        return testResultDto;
    }

}

package com.project.userManagementTest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String name;
}

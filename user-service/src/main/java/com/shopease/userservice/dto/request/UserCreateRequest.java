package com.shopease.userservice.dto.request;

import com.shopease.userservice.entity.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class UserCreateRequest {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @Email
    private String email;

    private Roles role;


    private Timestamp createAt;
    private String message;

}

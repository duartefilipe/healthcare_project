package com.example.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String document;
    private LocalDate birthDate;
    private String username;
    private String password;
    private String role;
}
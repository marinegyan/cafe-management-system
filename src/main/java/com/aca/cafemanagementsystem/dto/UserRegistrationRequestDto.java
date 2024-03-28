package com.aca.cafemanagementsystem.dto;


import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    private String name;
    private String email;
    private String password;
}

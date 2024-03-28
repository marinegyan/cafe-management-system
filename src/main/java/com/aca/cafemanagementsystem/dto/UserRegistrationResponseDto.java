package com.aca.cafemanagementsystem.dto;


import lombok.Data;

@Data
public class UserRegistrationResponseDto {
    private String name;
    private String email;
    private Long id;
}

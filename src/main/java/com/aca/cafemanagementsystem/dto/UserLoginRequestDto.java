package com.aca.cafemanagementsystem.dto;


import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String email;
    private String password;
}

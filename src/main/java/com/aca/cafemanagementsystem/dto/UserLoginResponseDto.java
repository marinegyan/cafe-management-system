package com.aca.cafemanagementsystem.dto;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    private String email;
    private String accessToken;
}

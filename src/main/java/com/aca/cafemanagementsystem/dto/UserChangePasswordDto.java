package com.aca.cafemanagementsystem.dto;

import lombok.Data;

@Data
public class UserChangePasswordDto {
    String email;
    String newPassword;
    String oldPassword;
}

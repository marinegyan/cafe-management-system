package com.aca.cafemanagementsystem.service;

import com.aca.cafemanagementsystem.dto.*;
import com.aca.cafemanagementsystem.exceptions.InvalidUsernamePasswordException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    UserRegistrationResponseDto signup(UserRegistrationRequestDto userRegistrationRequestDto);

    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) throws InvalidUsernamePasswordException;

    ResponseEntity<String> update(UserUpdateDto userUpdateDto);

    ResponseEntity<String> changePassword(UserChangePasswordDto userChangePasswordDto);
    ManagerCredentialsDto createManagerCredentials();

}

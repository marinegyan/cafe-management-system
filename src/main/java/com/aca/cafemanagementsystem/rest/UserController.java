package com.aca.cafemanagementsystem.rest;

import com.aca.cafemanagementsystem.dto.*;
import com.aca.cafemanagementsystem.exceptions.InvalidUsernamePasswordException;
import com.aca.cafemanagementsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        try {
            UserRegistrationResponseDto responseDto = userService.signup(userRegistrationRequestDto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        try {
            return ResponseEntity.ok(userService.login(userLoginRequestDto));
        } catch (InvalidUsernamePasswordException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(userUpdateDto);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody UserChangePasswordDto userChangePasswordDto) {
        return userService.changePassword(userChangePasswordDto);
    }
    @PostMapping("/create-manager")
    public ResponseEntity<ManagerCredentialsDto> createManager() {
        ManagerCredentialsDto managerCredentialsDto = userService.createManagerCredentials();
        return ResponseEntity.ok(managerCredentialsDto);
    }
}

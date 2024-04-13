package com.aca.cafemanagementsystem.serviceimpl;

import com.aca.cafemanagementsystem.dao.UserRepository;
import com.aca.cafemanagementsystem.dto.*;
import com.aca.cafemanagementsystem.exceptions.InvalidUsernamePasswordException;
import com.aca.cafemanagementsystem.exceptions.UserRegistrationException;
import com.aca.cafemanagementsystem.model.Role;
import com.aca.cafemanagementsystem.model.User;
import com.aca.cafemanagementsystem.service.UserService;
import com.aca.cafemanagementsystem.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserRegistrationResponseDto signup(UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = new User();
        user.setEmail(userRegistrationRequestDto.getEmail());
        user.setName(userRegistrationRequestDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationRequestDto.getPassword()));
        user.setRole(Role.WAITER);
        User savedUser = userRepository.save(user);
        UserRegistrationResponseDto userRegistrationResponseDto = new UserRegistrationResponseDto();
        userRegistrationResponseDto.setEmail(savedUser.getEmail());
        userRegistrationResponseDto.setName(savedUser.getName());
        userRegistrationResponseDto.setId(savedUser.getId());
        return userRegistrationResponseDto;
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) throws InvalidUsernamePasswordException {
        Optional<User> userOptional = userRepository.findByEmail(userLoginRequestDto.getEmail());
        if (userOptional.isEmpty()) {
            throw new InvalidUsernamePasswordException("Invalid Username Password");
        }
        User user = userOptional.get();
        if (!bCryptPasswordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidUsernamePasswordException("Invalid Username Password");
        }
        String token = jwtUtils.createToken(user);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setEmail(user.getEmail());
        userLoginResponseDto.setAccessToken(token);
        return userLoginResponseDto;
    }

    @Override
    public ResponseEntity<String> update(UserUpdateDto userUpdateDto) {
        Optional<User> userOptional = userRepository.findByEmail(userUpdateDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userUpdateDto.getName());
            user.setEmail(userUpdateDto.getEmail());
            userRepository.save(user);
            return ResponseEntity.ok("User details updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @Override
    public ResponseEntity<String> changePassword(UserChangePasswordDto userChangePasswordDto) {
        Optional<User> userOptional = userRepository.findByEmail(userChangePasswordDto.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (bCryptPasswordEncoder.matches(userChangePasswordDto.getOldPassword(), user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(userChangePasswordDto.getNewPassword()));
                userRepository.save(user);
                return ResponseEntity.ok("Password was changes successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid old password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User was not found");
        }
    }

    @Override
    public ManagerCredentialsDto createManagerCredentials() {
        String managerUsername = "manager";
        String managerPassword;

        String uniqueUsername = generateUniqueUsername(managerUsername);

        do {
            managerPassword = generateRandomPassword();
        } while (!isPasswordUnique(managerPassword));

        User managerUser = new User();
        managerUser.setEmail(uniqueUsername + "@example.com");
        managerUser.setName("Manager");
        managerUser.setPassword(bCryptPasswordEncoder.encode(managerPassword));
        managerUser.setRole(Role.MANAGER);

        User savedManager = userRepository.save(managerUser);

        ManagerCredentialsDto managerCredentialsDto = new ManagerCredentialsDto();
        managerCredentialsDto.setName(savedManager.getName());
        managerCredentialsDto.setPassword(managerPassword);

        return managerCredentialsDto;
    }

    private String generateUniqueUsername(String baseUsername) {
        String username = baseUsername;
        int counter = 1;

        while (userRepository.existsByEmail(username + "@example.com")) {
            username = baseUsername + counter;
            counter++;
        }

        return username;
    }

    private boolean isPasswordUnique(String password) {
        return true;
    }

    private String generateRandomPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = upperCaseLetters.toLowerCase();
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+";
        String allCharacters = upperCaseLetters + lowerCaseLetters + numbers + specialCharacters;
        int passwordLength = 10;
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            passwordBuilder.append(allCharacters.charAt(randomIndex));
        }

        return passwordBuilder.toString();
    }

}

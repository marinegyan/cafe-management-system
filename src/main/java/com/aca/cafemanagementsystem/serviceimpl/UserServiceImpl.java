package com.aca.cafemanagementsystem.serviceimpl;

import com.aca.cafemanagementsystem.service.UserService;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return null;
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        return null;
    }
}

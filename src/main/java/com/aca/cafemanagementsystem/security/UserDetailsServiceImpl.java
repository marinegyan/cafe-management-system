package com.aca.cafemanagementsystem.security;

import com.aca.cafemanagementsystem.model.User;
import com.aca.cafemanagementsystem.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        User presentUser;
        if (user.isPresent()) {
            presentUser = user.get();
        } else {
            throw new RuntimeException("User With Email doesn't exists");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(presentUser.getEmail())
                .password(presentUser.getPassword())
                .roles(new String[]{presentUser.getRole()})
                .build();
    }
}

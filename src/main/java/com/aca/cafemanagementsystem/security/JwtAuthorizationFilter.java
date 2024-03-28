package com.aca.cafemanagementsystem.security;

import com.aca.cafemanagementsystem.model.User;
import com.aca.cafemanagementsystem.repository.UserRepository;
import com.aca.cafemanagementsystem.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.InvalidClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtil;
    private final ObjectMapper mapper;

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(JwtUtils jwtUtil, ObjectMapper mapper, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("token : " + accessToken);
            Claims claims = jwtUtil.resolveClaims(request);

            if (claims != null & jwtUtil.validateClaims(claims)) {
                String email = claims.getSubject();
                Optional<User> userOptional = userRepository.findByEmail(email);
                if (userOptional.isEmpty()) {
                    throw new AuthenticationException("Invalid Username Password Exception");
                }
                System.out.println("email : " + email);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                                                                            email, userOptional.get().getPassword(),
                                                                                            List.of(new Authority(userOptional.get().getRole())));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                throw new AuthenticationException("Token Expired");
            }

        } catch (InvalidClaimException | AuthenticationException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
        filterChain.doFilter(request, response);
    }


    private static class Authority implements GrantedAuthority {
        private String authority;

        public Authority(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return authority;
        }
    }
}

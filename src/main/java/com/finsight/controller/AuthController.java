package com.finsight.controller;

import com.finsight.DTO.request.LoginDTO;
import com.finsight.DTO.request.RegisterUserDTO;  // Используйте правильный DTO
import com.finsight.entity.User;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;  // Используйте AuthService, а не UserService
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService authService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    // Регистрация нового пользователя
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        try {
            User user = authService.register(registerUserDTO);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Логин пользователя
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        // Успешная аутентификация
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    // Пример выхода из системы
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}

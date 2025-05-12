package com.finsight.controller;

import com.finsight.DTO.request.RegisterUserDTO;
import com.finsight.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDTO registerUserDTO) {
        try {
            authService.register(registerUserDTO);
            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
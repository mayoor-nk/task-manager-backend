package com.example.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.dto.LoginRequest;
import com.example.task.dto.RegisterRequest;
import com.example.task.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String response = authService.register(request);
        if (response.equals("Username already taken")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        if (token.equals("Invalid credentials")) {
            return ResponseEntity.status(401).body(token);
        }
        return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
    }
}

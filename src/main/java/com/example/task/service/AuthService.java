package com.example.task.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.task.dto.LoginRequest;
import com.example.task.dto.RegisterRequest;
import com.example.task.jwt.JwtUtil;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already taken";
        }

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isPresent() && passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            // We’ll add JWT generation here later
            return jwtUtil.generateToken(request.getUsername());
        }
        return "Invalid credentials";
    }
}

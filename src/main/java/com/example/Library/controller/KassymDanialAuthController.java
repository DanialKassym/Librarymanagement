package com.example.Library.controller;

import com.example.Library.controller.dto.AuthRequestDto;
import com.example.Library.domain.Role;
import com.example.Library.domain.User;
import com.example.Library.domain.repository.UserRepository;

import com.example.Library.util.KassymDanialJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class KassymDanialAuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KassymDanialJwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequestDto request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDto request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
package com.example.Jwt.Implement.controller;

import com.example.Jwt.Implement.dto.LogInRequestDto;
import com.example.Jwt.Implement.dto.LoginResponseDto;
import com.example.Jwt.Implement.dto.SignUpResponseDto;
import com.example.Jwt.Implement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private  final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LogInRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody LogInRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));
    }
}

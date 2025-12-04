package com.example.Jwt.Implement.service;

import com.example.Jwt.Implement.dto.LogInRequestDto;
import com.example.Jwt.Implement.dto.LoginResponseDto;
import com.example.Jwt.Implement.dto.SignUpResponseDto;
import com.example.Jwt.Implement.entity.User;
import com.example.Jwt.Implement.repository.UserRepository;
import com.example.Jwt.Implement.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LogInRequestDto logInRequestDto){
        LOGGER.info("Inside of loginService()::");
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(logInRequestDto.getUsername(), logInRequestDto.getPassword())
        );
        User user = (User)authentication.getPrincipal();
        String token = authUtil.generateAccessToke(user);
        return new LoginResponseDto(token,user.getId());
    }

    public SignUpResponseDto  signUp(LogInRequestDto signUpRequestDto) {
        User user = userRepository.findByUserName(signUpRequestDto.getUsername()).orElse(null);
        if (user != null) throw new IllegalArgumentException("User already exist");
        user = userRepository.save(User.builder()
                .userName(signUpRequestDto.getUsername())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()) )
                .build()
        );
        return new SignUpResponseDto(user.getId(),user.getUsername());
    }
}

package com.example.Jwt.Implement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequestDto {
    private String username;
    private String password;
}

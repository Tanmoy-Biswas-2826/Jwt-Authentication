package com.example.Jwt.Implement.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handelUserNameNotFoundException(UsernameNotFoundException ex){
        ApiError apiError = new ApiError("Username not found: "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiError>(apiError,apiError.getHttpStatus());
    }
}

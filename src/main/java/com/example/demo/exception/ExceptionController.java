package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnAuthorizedException.class)
    protected ResponseEntity<?> handleUnAuthorizedException(Exception ex, WebRequest request){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<?> handleUsernameNotFoundException(Exception ex, WebRequest request){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}

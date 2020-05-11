package com.ctc.itlease.taskmanager.controller;

import com.ctc.itlease.taskmanager.exception.AppException;
import com.ctc.itlease.taskmanager.payload.ApiResponse;
import com.ctc.itlease.taskmanager.payload.JwtAuthenticationResponse;
import com.ctc.itlease.taskmanager.payload.LoginRequest;
import com.ctc.itlease.taskmanager.payload.SignUpRequest;
import com.ctc.itlease.taskmanager.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authendicateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String authToken = authService.authenticate(loginRequest);
        return ResponseEntity.ok(new JwtAuthenticationResponse(authToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            authService.registerUser(signUpRequest);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/users/{username}")
                    .buildAndExpand(signUpRequest.getUsername())
                    .toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
        } catch (AppException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}

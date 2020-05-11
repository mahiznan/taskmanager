package com.ctc.itlease.taskmanager.service.impl;

import com.ctc.itlease.taskmanager.exception.AppException;
import com.ctc.itlease.taskmanager.model.Role;
import com.ctc.itlease.taskmanager.model.RoleName;
import com.ctc.itlease.taskmanager.model.User;
import com.ctc.itlease.taskmanager.payload.LoginRequest;
import com.ctc.itlease.taskmanager.payload.SignUpRequest;
import com.ctc.itlease.taskmanager.repository.RoleRepository;
import com.ctc.itlease.taskmanager.repository.UserRepository;
import com.ctc.itlease.taskmanager.security.JWTTokenProvider;
import com.ctc.itlease.taskmanager.service.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider tokenProvider;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    @Override
    public void registerUser(SignUpRequest signUpRequest) throws AppException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AppException("Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AppException("Email address already in use");
        }
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set"));
        user.addRole(userRole);
        userRepository.save(user);
    }
}

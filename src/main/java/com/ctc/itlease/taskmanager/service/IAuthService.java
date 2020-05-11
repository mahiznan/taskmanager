package com.ctc.itlease.taskmanager.service;

import com.ctc.itlease.taskmanager.exception.AppException;
import com.ctc.itlease.taskmanager.payload.LoginRequest;
import com.ctc.itlease.taskmanager.payload.SignUpRequest;

public interface IAuthService {
    String authenticate(LoginRequest loginRequest);

    void registerUser(SignUpRequest signUpRequest) throws AppException;
}

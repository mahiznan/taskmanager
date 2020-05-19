package com.ctc.itlease.taskmanager.auth;

import com.ctc.itlease.taskmanager.exception.AppException;
import com.ctc.itlease.taskmanager.auth.LoginRequest;
import com.ctc.itlease.taskmanager.auth.SignUpRequest;

public interface IAuthService {
    String authenticate(LoginRequest loginRequest);

    void registerUser(SignUpRequest signUpRequest) throws AppException;
}

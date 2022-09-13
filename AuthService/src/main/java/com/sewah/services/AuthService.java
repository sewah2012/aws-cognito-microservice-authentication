package com.sewah.services;

import com.sewah.dto.LoginRequest;
import com.sewah.dto.SignupRequest;

public interface AuthService {

    Object signup(SignupRequest request);
    Object login(LoginRequest loginRequest);
}

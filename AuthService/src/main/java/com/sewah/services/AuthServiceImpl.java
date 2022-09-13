package com.sewah.services;

import com.sewah.dto.LoginRequest;
import com.sewah.dto.SignupRequest;
import com.sewah.utils.CognitoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CognitoUtils cognitoUtils;
    @Override
    public Object login(LoginRequest loginRequest) {
        return "Login Done "+ loginRequest.getEmail();
    }

    @Override
    public Object signup(SignupRequest request) {
        return cognitoUtils.signup(request);
    }
}

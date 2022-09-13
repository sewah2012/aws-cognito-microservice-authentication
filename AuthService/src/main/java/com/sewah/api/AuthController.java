package com.sewah.api;

import com.sewah.dto.LoginRequest;
import com.sewah.dto.SignupRequest;
import com.sewah.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    @PostMapping("/login")
    public ResponseEntity<?> userInfo(@RequestBody() LoginRequest loginRequest){
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody() SignupRequest request){
        return ResponseEntity.ok(service.signup(request));
    }
}

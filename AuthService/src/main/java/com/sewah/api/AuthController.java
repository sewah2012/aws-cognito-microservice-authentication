package com.sewah.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    
    @GetMapping("/user-info")
    public String userInfo(){
        return "This is the user info lol";
    }
}

package com.sewah.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nationality;
    private String phoneNumber;

    @Builder.Default
    private Set<String> roles = Set.of("ROLE_USER");
}

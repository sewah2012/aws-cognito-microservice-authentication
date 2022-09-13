package com.sewah.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "aws", ignoreUnknownFields = false)
public class AwsConfig {
    private String accessKey;
    private String secretKey;
    private String region;
    private final Cognito cognito = new Cognito();

    @Getter
    @Setter
    public static class Cognito{
        private String userPoolId;
        private String appClientId;
        private String appClientSecret;


    }
}

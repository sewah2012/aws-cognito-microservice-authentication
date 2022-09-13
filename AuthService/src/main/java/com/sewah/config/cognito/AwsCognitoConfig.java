package com.sewah.config.cognito;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.sewah.config.AwsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsCognitoConfig {
    private final AwsConfig awsConfig;

    @Bean
    AWSCognitoIdentityProvider awsCognitoIdentityProviderClient(){
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                                awsConfig.getAccessKey(),
                                awsConfig.getSecretKey()
                        ))
                )
                .withRegion(awsConfig.getRegion())
                .build();
    }

}

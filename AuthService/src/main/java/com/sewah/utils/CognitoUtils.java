package com.sewah.utils;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.sewah.config.AwsConfig;
import com.sewah.dto.SignupRequest;
import exceptions.errors.FailedAuthenticationException;
import exceptions.errors.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CognitoUtils {
    private final AwsConfig awsConfig;
    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;

   public UserType signup(SignupRequest request) {
        try {
            final AdminCreateUserRequest signUpRequest = new AdminCreateUserRequest()
                    .withUserPoolId(awsConfig.getCognito().getUserPoolId())
                    // The user's temporary password.
                    .withTemporaryPassword(request.getPassword())
                    // Specify "EMAIL" if email will be used to send the welcome message
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
                    .withUsername(request.getEmail())
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withUserAttributes(
                            new AttributeType().withName("name").withValue(request.getFirstName()),
                            new AttributeType().withName("family_name").withValue(request.getLastName()),
                            new AttributeType().withName("custom:nationality").withValue(request.getNationality()),
                            new AttributeType().withName("email").withValue(request.getEmail()),
                            new AttributeType().withName("email_verified").withValue("false"),
                            new AttributeType().withName("phone_number").withValue(request.getPhoneNumber()),
                            new AttributeType().withName("phone_number_verified").withValue("true")
                    );

            // create user
            var createUserResult = awsCognitoIdentityProvider.adminCreateUser(signUpRequest);
            log.info("Created User id: {}", createUserResult.getUser().getUsername());

            // assign the roles
            request.getRoles().forEach(r -> addUserToGroup(request.getEmail(), r));

            // set permanent password
            setUserPassword(request.getEmail(), request.getPassword());

            return createUserResult.getUser();

        } catch (com.amazonaws.services.cognitoidp.model.UsernameExistsException e) {
            throw new UserAlreadyExistsException("User name that already exists");
        } catch (com.amazonaws.services.cognitoidp.model.InvalidPasswordException e) {
            throw new InvalidPasswordException(e.getLocalizedMessage());
        }
    }


    public void addUserToGroup(String username, String groupName) {

        try {
            // add user to group
            AdminAddUserToGroupRequest addUserToGroupRequest = new AdminAddUserToGroupRequest()
                    .withGroupName(groupName)
                    .withUserPoolId(awsConfig.getCognito().getUserPoolId())
                    .withUsername(username);

            awsCognitoIdentityProvider.adminAddUserToGroup(addUserToGroupRequest);
        } catch (com.amazonaws.services.cognitoidp.model.InvalidPasswordException e) {
            throw new FailedAuthenticationException(String.format("Invalid parameter: %s", e.getErrorMessage()));
        }
    }

    /**
     * {@inheritDoc}
     */

    public void setUserPassword(String username, String password) {
        try {
            // Sets the specified user's password in a user pool as an administrator. Works on any user.
            AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest()
                    .withUsername(username)
                    .withPassword(password)
                    .withUserPoolId(awsConfig.getCognito().getUserPoolId())
                    .withPermanent(true);

            awsCognitoIdentityProvider.adminSetUserPassword(adminSetUserPasswordRequest);
        } catch (InvalidPasswordException e) {
            throw new FailedAuthenticationException(String.format("Invalid parameter: %s", e.getErrorMessage()));
        }
    }





}

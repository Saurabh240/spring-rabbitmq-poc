package com.rabbitmq.poc.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminAddUserToGroupRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

@Service
public class UserService {

    private final CognitoIdentityProviderClient cognitoClient;
    private final String userPoolId;

    public UserService(CognitoIdentityProviderClient cognitoClient) {
        this.cognitoClient = cognitoClient;
        this.userPoolId = "ap-south-1_igKxdRofH";
    }

    public void createUser(String email, String password, String companyName, String role) {

        AttributeType emailVerifiedAttr = AttributeType.builder()
                .name("email_verified")
                .value("true")
                .build();

        cognitoClient.adminCreateUser(AdminCreateUserRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .userAttributes(emailVerifiedAttr)
                .temporaryPassword(password)
                .build());

        cognitoClient.adminAddUserToGroup(AdminAddUserToGroupRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .groupName(companyName + "-" + role)
                .build());
    }
}

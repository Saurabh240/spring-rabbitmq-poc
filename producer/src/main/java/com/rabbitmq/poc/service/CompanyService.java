package com.rabbitmq.poc.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateGroupRequest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Service
public class CompanyService {

    private final CognitoIdentityProviderClient cognitoClient;
    private final DataSource dataSource;

    public CompanyService(CognitoIdentityProviderClient cognitoClient, DataSource dataSource) {
        this.cognitoClient = cognitoClient;
        this.dataSource = dataSource;
    }

    public void createCompany(String companyName) throws Exception {
        // 1. Create groups
        cognitoClient.createGroup(CreateGroupRequest.builder().groupName(companyName + "-Admin").build());
        cognitoClient.createGroup(CreateGroupRequest.builder().groupName(companyName + "-User").build());

        // 2. Create schema
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS " + companyName);
            stmt.execute("CREATE TABLE IF NOT EXISTS " + companyName + ".transaction (" +
                    "id UUID PRIMARY KEY, product VARCHAR(255), quantity INTEGER, " +
                    "received_at TIMESTAMP, company_id VARCHAR(255), user_id VARCHAR(255))");
        }
    }
}
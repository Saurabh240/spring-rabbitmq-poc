package com.rabbitmq.poc.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        try {
            JwtAuthenticationToken auth = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            Object claim = auth.getTokenAttributes().get("custom:companyId");
            return claim != null ? claim.toString() : DEFAULT_TENANT;
        } catch (Exception e) {
            return DEFAULT_TENANT;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}


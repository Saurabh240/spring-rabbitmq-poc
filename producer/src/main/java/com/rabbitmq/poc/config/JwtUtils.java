package com.rabbitmq.poc.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    public String getUserId() {
        return getClaim("sub");
    }

    public String getCompanyId() {
        return getClaim("companyId");
    }

    public String getRole() {
        return getClaim("role");
    }

    private String getClaim(String claimName) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            Object claim = jwt.getClaims().get(claimName);
            return claim != null ? claim.toString() : null;
        }
        return null;
    }
}

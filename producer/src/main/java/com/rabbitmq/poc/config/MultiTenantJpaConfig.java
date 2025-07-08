package com.rabbitmq.poc.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiTenantJpaConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        return new SchemaMultiTenantConnectionProvider(dataSource);
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new TenantIdentifierResolver();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> hibernateProps = new HashMap<>();
        hibernateProps.put("hibernate.multiTenancy", "SCHEMA");
        hibernateProps.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProvider());
        hibernateProps.put("hibernate.tenant_identifier_resolver", currentTenantIdentifierResolver());
        hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.rabbitmq.poc.model")
                .persistenceUnit("default")
                .properties(hibernateProps)
                .build();
    }
}

package com.rabbitmq.poc.config;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider {

    private final DataSource dataSource;
    private final Map<String, Connection> tenantConnections = new ConcurrentHashMap<>();

    public SchemaMultiTenantConnectionProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection(Object tenantIdentifier) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setSchema((String) tenantIdentifier);
        return connection;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(Object o, Connection connection) throws SQLException {

    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    // Other interface methods return defaults
    @Override public boolean isUnwrappableAs(Class unwrapType) { return false; }
    @Override public <T> T unwrap(Class<T> unwrapType) { return null; }
}

package com.rabbitmq.poc.service;

import com.rabbitmq.poc.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Service
public class TransactionConsumerService {
    @Autowired
    private DataSource dataSource;

    public void saveToSchema(String schema, Transaction tx) {
        String sql = "INSERT INTO \"" + schema + "\".transaction (" +
                "id, company_id, company_name, device_id, header, raw_sms, received_at, staff_name, timestamp, user_id" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, tx.getId());
            stmt.setString(2, tx.getCompanyId());
            stmt.setString(3, tx.getCompanyName());
            stmt.setString(4, tx.getDeviceId());
            stmt.setString(5, tx.getHeader());
            stmt.setString(6, tx.getRawSms());
            stmt.setTimestamp(7, Timestamp.valueOf(tx.getReceivedAt()));
            stmt.setString(8, tx.getStaffName());
            stmt.setTimestamp(9, Timestamp.valueOf(tx.getTimestamp()));
            stmt.setString(10, tx.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed for schema: " + schema, e);
        }
    }
}
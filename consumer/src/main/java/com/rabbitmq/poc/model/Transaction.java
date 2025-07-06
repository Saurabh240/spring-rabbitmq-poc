package com.rabbitmq.poc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Transaction {
    @Id
    private UUID id;

    private UUID companyId;
    private UUID userId;

    private String header;
    private String companyName;
    private String staffName;
    private String deviceId;
    private String rawSms;

    private LocalDateTime timestamp;
    private LocalDateTime receivedAt;
}
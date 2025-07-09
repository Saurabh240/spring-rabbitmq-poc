package com.rabbitmq.poc.model;

import lombok.Data;

@Data
public class TransactionRequest {
    private String header;
    private String companyName;
    private String staffName;
    private String deviceId;
    private String rawSms;
    private String userId;
    private String companyId;
    private String timestamp;
}
package com.rabbitmq.poc.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TransactionRequest {
    @NotBlank private String header;
    @NotBlank private String companyName;
    @NotBlank private String staffName;
    @NotBlank private String deviceId;
    @NotBlank private String rawSms;
    @NotBlank private String timestamp;
}
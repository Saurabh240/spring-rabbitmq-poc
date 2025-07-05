package com.rabbitmq.poc;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRequest {
    private String orderId;
    private String product;
    private int quantity;
}


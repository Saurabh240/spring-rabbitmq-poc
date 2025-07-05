package com.rabbitmq.poc;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    private String orderId;
    private String product;
    private int quantity;
    private LocalDateTime receivedAt;
}

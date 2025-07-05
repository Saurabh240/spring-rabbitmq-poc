package com.rabbitmq.poc;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderProcessor {
    private final com.rabbitmq.poc.OrderRepository repository;

    public OrderProcessor(OrderRepository repository) {
        this.repository = repository;
    }

    public void save(Order order) {
        order.setReceivedAt(LocalDateTime.now());
        repository.save(order);
    }
}

package com.rabbitmq.poc.service;

import com.rabbitmq.poc.model.Transaction;
import com.rabbitmq.poc.repository.TransactionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionListener {

    private final TransactionRepository repository;

    public TransactionListener(TransactionRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "transaction.queue")
    public void handleMessage(Transaction tx) {
        tx.setId(UUID.randomUUID());
        tx.setReceivedAt(LocalDateTime.now());
        repository.save(tx);
    }
}

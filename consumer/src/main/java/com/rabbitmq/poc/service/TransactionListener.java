package com.rabbitmq.poc.service;

import com.rabbitmq.poc.model.Transaction;
import com.rabbitmq.poc.model.TransactionRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionListener {

    private final TransactionConsumerService consumerService;

    public TransactionListener(TransactionConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @RabbitListener(queues = "transaction.queue")
    public void handleMessage(TransactionRequest request) {
        Transaction tx = new Transaction();
        tx.setId(UUID.randomUUID());
        tx.setCompanyName(request.getCompanyName());
        tx.setDeviceId(request.getDeviceId());
        tx.setHeader(request.getHeader());
        tx.setRawSms(request.getRawSms());
        tx.setReceivedAt(LocalDateTime.now());
        tx.setStaffName(request.getStaffName());
        tx.setTimestamp(LocalDateTime.now());
        tx.setUserId(request.getUserId());
        consumerService.saveToSchema(request.getCompanyName(), tx);
    }
}

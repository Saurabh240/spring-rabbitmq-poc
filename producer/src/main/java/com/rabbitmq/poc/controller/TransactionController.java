package com.rabbitmq.poc.controller;

import com.rabbitmq.poc.config.JwtUtils;
import com.rabbitmq.poc.request.TransactionRequest;
import com.rabbitmq.poc.service.MessagePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final MessagePublisher publisher;
    private final JwtUtils jwtUtils;

    public TransactionController(MessagePublisher publisher, JwtUtils jwtUtils) {
        this.publisher = publisher;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionRequest request) {
        // Attach company_id, user_id if needed
        publisher.publish(request);
        return ResponseEntity.ok("Queued successfully");
    }
}

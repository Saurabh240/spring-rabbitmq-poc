package com.rabbitmq.poc.controller;

import com.rabbitmq.poc.request.TransactionRequest;
import com.rabbitmq.poc.service.MessagePublisher;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
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

    public TransactionController(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionRequest request, HttpServletRequest httpServletRequest) throws Exception {
        Claims claims = (Claims) httpServletRequest.getAttribute("claims");
        if (claims == null || !"ROLE_user".equals(claims.get("role"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only users can ingest transactions.");
        }
        request.setUserId(claims.get("sub", String.class));
        request.setCompanyId(claims.get("companyName", String.class));
        publisher.publish(request);
        return ResponseEntity.ok("Queued successfully");
    }
}

package com.rabbitmq.poc.controller;

import com.rabbitmq.poc.config.JwtUtils;
import com.rabbitmq.poc.request.TransactionRequest;
import com.rabbitmq.poc.service.CompanyService;
import com.rabbitmq.poc.service.MessagePublisher;
import com.rabbitmq.poc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final CompanyService companyService;
    private final UserService userService;
    private final MessagePublisher publisher;
    private final JwtUtils jwtUtils;

    public TransactionController(CompanyService companyService, UserService userService, MessagePublisher publisher, JwtUtils jwtUtils) {
        this.companyService = companyService;
        this.userService = userService;
        this.publisher = publisher;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionRequest request) throws Exception {
        companyService.createCompany(request.getCompanyName());
        userService.createUser(request.getDeviceId(),"abcdefgh", request.getCompanyName(), "USER");
        publisher.publish(request);
        return ResponseEntity.ok("Queued successfully");
    }
}

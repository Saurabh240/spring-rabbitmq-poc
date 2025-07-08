package com.rabbitmq.poc.controller;

import com.rabbitmq.poc.service.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public String createCompany(@RequestParam String companyName) throws Exception {
        companyService.createCompany(companyName);
        return "Company created with schema and Cognito groups.";
    }
}

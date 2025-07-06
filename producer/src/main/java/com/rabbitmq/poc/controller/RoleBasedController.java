package com.rabbitmq.poc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleBasedController {

    @GetMapping("/admin/data")
    @PreAuthorize("hasRole('admin')")
    public String getAdminData() {
        return "Secure data for ADMIN only.";
    }

    @GetMapping("/user/data")
    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String getUserData() {
        return "Data accessible by USER or ADMIN.";
    }
}

package com.rabbitmq.poc.controller;

import com.rabbitmq.poc.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String company,
                             @RequestParam String role) {

        userService.createUser(email, password, company, role);

        return "User created and added to group.";
    }
}
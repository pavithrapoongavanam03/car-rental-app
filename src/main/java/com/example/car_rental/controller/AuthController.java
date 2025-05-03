package com.example.car_rental.controller;

import com.example.car_rental.model.User;
import com.example.car_rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginReq) {
        Optional<User> user = userService.authenticate(loginReq.getEmail(), loginReq.getPassword());
        return user.map(value -> "Login successful! Welcome " + value.getFullName())
                   .orElse("Invalid email or password.");
    }
}

package com.app.quantitymeasurement.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.app.quantitymeasurement.security.JwtUtil;
import com.app.quantitymeasurement.DTO.LoginResponse;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.DTO.LoginRequest;
import com.app.quantitymeasurement.DTO.RegisterRequest;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // ✅ Register User
    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userService.registerUser(user);
    }
 
    // ✅ Get User by Email
    @GetMapping("/user")
    public User getUser(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {

        User user = userService.loginUser(request.getEmail(), request.getPassword());

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }
   
   
}
package com.core.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.UserCredentials;
import com.core.apirest.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserCredentials userCredentials) {
        // Check if user exists
        try {
            userService.getUser(userCredentials);
        } catch (Exception e) {
            // return bad request
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        // Generate JWT token
        String token = jwtUtil.generateToken(userCredentials.username);
        // Return the token to the client
        return ResponseEntity.ok(token);
    }
}

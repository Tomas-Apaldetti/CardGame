package com.core.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.User;
import com.core.apirest.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class TestController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody final User user) {
        if (userService.existsUser(user.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists");
        } else {
            userService.addUser(user);
            return ResponseEntity.ok("User created successfully");
        }
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUsersWithoutPasswords();
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable final String username) {
        return userService.getUserWithOutPasswrod(username);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Check if user exists
        User returnUser = userService.getUser(user.getUsername());
        if (returnUser == null || !returnUser.getPassword().equals(user.getPassword())) {
            return "Not valid credentials";
        }
        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername());
        // Return the token to the client
        return token;
    }

    @GetMapping("/user/money/{username}")
    public int getMoney(@RequestHeader("my-authorization") String token, @PathVariable final String username) {
        String extractedToken = token.substring(7);
        String extractedUsername = jwtUtil.extractUsername(extractedToken);
        if (!extractedUsername.equals(username)) {
            return -1;
        }
        User returnUser = userService.getUser(extractedUsername);
        if (returnUser == null) {
            return -1;
        } else {
            return returnUser.getMoney();
        }
    }

    @PostMapping("/user/money/{username}")
    public int addMoney(@RequestHeader("my-authorization") String token, @PathVariable final String username,
            @RequestBody final int moneyToAdd) {
        String extractedToken = token.substring(7);
        String extractedUsername = jwtUtil.extractUsername(extractedToken);
        if (!extractedUsername.equals(username)) {
            return -1;
        }
        User returnUser = userService.getUser(extractedUsername);
        if (returnUser == null) {
            return -1;
        } else {
            return returnUser.addMoney(moneyToAdd);
        }
    }
}

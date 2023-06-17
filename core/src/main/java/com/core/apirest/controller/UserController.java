package com.core.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.User;
import com.core.apirest.model.UserCredentials;
import com.core.apirest.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    // Check this to define the endpoints URIs:
    // https://restfulapi.net/resource-naming/

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody final UserCredentials userCredentials) {
        if (userService.existsUser(userCredentials)) {
            return ResponseEntity.badRequest().body("User already exists");
        } else {
            userService.addUser(userCredentials);
            return ResponseEntity.ok("User created successfully");
        }
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();

    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable final String username) {
        return userService.getUser(username);
    }

    @GetMapping("/{username}/money")
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

    @PostMapping("/{username}/money")
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

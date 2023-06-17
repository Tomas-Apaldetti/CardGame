package com.core.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.User;
import com.core.apirest.model.UserCredentials;
import com.core.apirest.model.UserMoney;
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
    public ResponseEntity<User> getUserByUsername(@PathVariable final String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/money")
    public ResponseEntity<UserMoney> getMoney(@RequestHeader("my-authorization") String token) {
        String extractedUsername = jwtUtil.extractUsername(token);

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        UserMoney userMoneyDTO = userService.getUserMoney(extractedUsername);
        return ResponseEntity.ok(userMoneyDTO);
    }

    @PostMapping("/money")
    public ResponseEntity<UserMoney> addMoney(@RequestHeader("my-authorization") String token,
            @RequestBody final int moneyToAdd) {
        String extractedUsername = jwtUtil.extractUsername(token);

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }
        if (moneyToAdd < 0) {
            return ResponseEntity.badRequest().build();
        }

        UserMoney userMoneyDTO = userService.addMoney(extractedUsername, moneyToAdd);
        return ResponseEntity.ok(userMoneyDTO);
    }

}

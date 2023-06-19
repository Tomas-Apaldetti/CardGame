package com.core.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.UserAPI;
import com.core.apirest.model.UserCredentials;
import com.core.apirest.service.UserService;
import com.core.g3.User.Exceptions.UserAlreadyExistsException;
import com.core.g3.User.Exceptions.UserDoesntExistException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        try {
            userService.addUser(userCredentials);
            return ResponseEntity.ok("User created successfully");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("User already exists");
        } catch (Exception e) {
            // if exception is of any other type
            return ResponseEntity.badRequest().body("Error creating user");
        }
    }

    @GetMapping
    @JsonIgnoreProperties({ "credentials" })
    public List<UserAPI> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/money")
    public ResponseEntity<Integer> getMoney(@RequestHeader("Authorization") String token) {
        System.out.println("inside getMoney");
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().build();
        }
        UserAPI user;
        try {
            user = userService.getUser(extractedUsername);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("User " + extractedUsername + " has " + user.user.getFounds());

       
        return ResponseEntity.ok(user.getMoney());
    }

    @PostMapping("/money")
    public ResponseEntity<String> addMoney(@RequestHeader("Authorization") String token,
            @RequestBody final int moneyToAdd) {
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().build();
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }
        if (moneyToAdd < 0) {
            return ResponseEntity.badRequest().build();
        }

        String message = userService.addMoney(extractedUsername, moneyToAdd);
        return ResponseEntity.ok(message);
    }

}

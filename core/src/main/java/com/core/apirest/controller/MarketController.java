package com.core.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.CardPriceDTO;
import com.core.apirest.model.UserAPI;
import com.core.apirest.service.MarketService;
import com.core.apirest.service.UserService;
import com.core.g3.User.Exceptions.UserDoesntExistException;

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
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("publish/{cardName}")
    public ResponseEntity<String> publish(@PathVariable String cardName, @RequestBody CardPriceDTO data , @RequestHeader("Authorization") String token) {

        int price = data.price;

        // extract user
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

        // publish card
        return marketService.sellCard(user, cardName, price);
    }

    @GetMapping("publications")
    public ResponseEntity<List<String>> getPublications() {
        // get publications
        List<String> response = marketService.getPublications();
        return ResponseEntity.ok(response);
    }

    @PostMapping("buy/{cardName}")
    public ResponseEntity<String> buy(@PathVariable String cardName, @RequestBody CardPriceDTO data, @RequestHeader("Authorization") String token) {
        Integer price = data.price;
        // extract user
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

        // buy card
        return marketService.buyCard(user, cardName, price);
    }

}

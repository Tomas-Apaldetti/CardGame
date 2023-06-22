package com.core.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.CardDTO;
import com.core.apirest.model.DeckDTO;
import com.core.apirest.model.MoneyDTO;
import com.core.apirest.model.UserAPI;
import com.core.apirest.model.UserCredentials;
import com.core.apirest.service.UserService;
import com.core.g3.User.Exceptions.UserAlreadyExistsException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().build();
        }

        return userService.getUserFounds(extractedUsername);
    }

    @PostMapping("/money")
    public ResponseEntity<String> addMoney(@RequestHeader("Authorization") String token,
            @RequestBody final MoneyDTO data) {
        Integer moneyToAdd = data.money;
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

    @GetMapping("/cards")
    public ResponseEntity<List<String>> getCards(@RequestHeader("Authorization") String token) {
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().build();
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        List<String> cards = userService.getCards(extractedUsername);
        return ResponseEntity.ok(cards);
    }

    @PostMapping("/card/buy")
    public ResponseEntity<String> buyCard(@RequestHeader("Authorization") String token,
            @RequestBody final CardDTO data) {
        String cardName = data.cardName;
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        String message = userService.buyCard(extractedUsername, cardName);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/decks")
    public ResponseEntity<List<String>> getDeck(@RequestHeader("Authorization") String token) {
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().build();
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        return userService.getDecks(extractedUsername);
    }

    @PostMapping("/decks")
    public ResponseEntity<String> addDeck(@RequestHeader("Authorization") String token,
            @RequestBody final DeckDTO data) {
        String deckName = data.deckName;
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        return userService.createDeck(extractedUsername, deckName);
    }

    @GetMapping("/decks/cards")
    public ResponseEntity<List<String>> getDeckCards(@RequestHeader("Authorization") String token,
            @RequestBody final DeckDTO data) {
        String deckName = data.deckName;
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().build();
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        return userService.getDecksCards(extractedUsername, deckName);
    }

    @PostMapping("/decks/cards")
    public ResponseEntity<String> addCardToDeck(@RequestHeader("Authorization") String token,
            @RequestBody final CardDTO data) {
        String cardName = data.cardName;
        String deckName = data.deckName;
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        return userService.addCardToDeck(extractedUsername, deckName, cardName);
    }

    @DeleteMapping("/decks/cards")
    public ResponseEntity<String> removeCardFromDeck(@RequestHeader("Authorization") String token,
            @RequestBody final CardDTO data) {
        String cardName = data.cardName;
        String deckName = data.deckName;
        String extractedUsername;
        try {
            extractedUsername = jwtUtil.extractUsername(token);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        if (userService.getUser(extractedUsername) == null) {
            return ResponseEntity.notFound().build();
        }

        return userService.removeCardFromDeck(extractedUsername, deckName, cardName);
    }
}

package com.core.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.apirest.jwtutil.JwtUtil;
import com.core.apirest.model.MatchInformation;
import com.core.apirest.model.NewMatch;
import com.core.apirest.model.PlayerMatchInformation;
import com.core.apirest.service.MatchService;
import com.core.apirest.service.exceptions.MatchAlreadyStartedException;
import com.core.apirest.service.exceptions.PlayerNotInGameException;
import com.core.g3.User.Exceptions.UserDoesntExistException;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MatchService matchService;

    @PostMapping
    public ResponseEntity<String> createMatch(@RequestBody final NewMatch match) {
        String bluePlayer = match.bluePlayer;
        String greenPlayer = match.greenPlayer;
        String gameMode = match.gameMode;
        if (bluePlayer == null || greenPlayer == null || gameMode == null) {
            return ResponseEntity.badRequest().body("Missing parameters");
        }
        if (bluePlayer.equals(greenPlayer)) {
            return ResponseEntity.badRequest().body("Players must be different");
        }
        if (!gameMode.equals("HitPointLoss") && !gameMode.equals("CreatureSlayer")) {
            return ResponseEntity.badRequest().body("Invalid game mode");
        }
        try {
            int gameId = matchService.createMatch(bluePlayer, greenPlayer, gameMode);
            String token = jwtUtil.generateToken(String.valueOf(gameId));
            return ResponseEntity.ok().body(token);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.badRequest().body("User doesn't exist");
        }
    }

    @GetMapping
    public ResponseEntity<MatchInformation> getMatch(@RequestHeader("my-authorization") String token) {
        String extractedMatchId = jwtUtil.extractUsername(token);
        int matchId = Integer.parseInt(extractedMatchId);
        if (matchId < 1 || matchId > matchService.totalGames) {
            return ResponseEntity.badRequest().build();
        }
        try {
            MatchInformation matchInformation = matchService.getMatchInformation(matchId);
            return ResponseEntity.ok().body(matchInformation);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping("/start")
    public ResponseEntity<MatchInformation> startMatch(@RequestHeader("my-authorization") String token) {
        String extractedMatchId = jwtUtil.extractUsername(token);
        int matchId = Integer.parseInt(extractedMatchId);
        if (matchId < 1 || matchId > matchService.totalGames) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok().body(matchService.startMatch(matchId));
        } catch (MatchAlreadyStartedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<PlayerMatchInformation> getPlayerInformation(@RequestHeader("my-authorization") String token,
            @PathVariable String username) {
        String extractedMatchId = jwtUtil.extractUsername(token);
        int matchId = Integer.parseInt(extractedMatchId);
        if (matchId < 1 || matchId > matchService.totalGames) {
            return ResponseEntity.badRequest().build();
        }
        try {
            PlayerMatchInformation playerMatchInformation = matchService.getPlayerMatchInformation(matchId, username);
            return ResponseEntity.ok().body(playerMatchInformation);
        } catch (PlayerNotInGameException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

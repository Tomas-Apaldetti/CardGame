package com.core.apirest.controller;

import java.util.List;

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
import com.core.apirest.model.ActivateAction;
import com.core.apirest.model.ActivateArtifact;
import com.core.apirest.model.CardAttack;
import com.core.apirest.model.CardInGameInformation;
import com.core.apirest.model.MatchInformation;
import com.core.apirest.model.NewMatch;
import com.core.apirest.model.PlayerMatchInformation;
import com.core.apirest.model.SkipToPhase;
import com.core.apirest.model.SummonCard;
import com.core.apirest.service.MatchService;
import com.core.apirest.service.exceptions.MatchAlreadyStartedException;
import com.core.apirest.service.exceptions.PlayerNotInGameException;
import com.core.g3.Card.CardName;
import com.core.g3.Match.Phase.PhaseType;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Zone.ActiveZoneType;
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

    // Reponer esta variable @RequestHeader("my-authorization") String token
    @GetMapping
    public ResponseEntity<MatchInformation> getMatch() {
        // String extractedMatchId = jwtUtil.extractUsername(token);
        // int matchId = Integer.parseInt(extractedMatchId);
        // if (matchId < 1 || matchId > matchService.totalGames) {
        // return ResponseEntity.badRequest().build();
        // }
        int matchId = 1;
        try {
            MatchInformation matchInformation = matchService.getMatchInformation(matchId);
            return ResponseEntity.ok().body(matchInformation);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping("/start")
    public ResponseEntity<MatchInformation> startMatch(@RequestHeader("my-authorization") String token) {
        // String extractedMatchId = jwtUtil.extractUsername(token);
        // int matchId = Integer.parseInt(extractedMatchId);
        // if (matchId < 1 || matchId > matchService.totalGames) {
        // return ResponseEntity.badRequest().build();
        // }
        int matchId = 1;
        try {
            return ResponseEntity.ok().body(matchService.startMatch(matchId));
        } catch (MatchAlreadyStartedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{player}")
    public ResponseEntity<PlayerMatchInformation> getPlayerInformation(@RequestHeader("my-authorization") String token,
            @PathVariable String player) {
        // String extractedMatchId = jwtUtil.extractUsername(token);
        // int matchId = Integer.parseInt(extractedMatchId);
        // if (matchId < 1 || matchId > matchService.totalGames) {
        // return ResponseEntity.badRequest().build();
        // }
        int matchId = 1;
        if (!player.equals("blue") && !player.equals("green")) {
            return ResponseEntity.badRequest().build();
        }
        PlayerZone playerZone;
        if (player.equals("blue")) {
            playerZone = PlayerZone.valueOf("Blue");
        } else {
            playerZone = PlayerZone.valueOf("Green");
        }
        try {
            PlayerMatchInformation playerMatchInformation = matchService.getPlayerMatchInformation(matchId, playerZone);
            return ResponseEntity.ok().body(playerMatchInformation);
        } catch (PlayerNotInGameException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{player}/hand")
    public ResponseEntity<List<CardInGameInformation>> getHand(@RequestHeader("my-authorization") String token,
            @PathVariable String player) {
        // Add validation to check who is viewing the hand
        // String extractedMatchId = jwtUtil.extractUsername(token);
        // int matchId = Integer.parseInt(extractedMatchId);
        // if (matchId < 1 || matchId > matchService.totalGames) {
        // return ResponseEntity.badRequest().build();
        // }
        int matchId = 1;
        if (!player.equals("blue") && !player.equals("green")) {
            return ResponseEntity.badRequest().build();
        }
        PlayerZone playerZone;
        if (player.equals("blue")) {
            playerZone = PlayerZone.valueOf("Blue");
        } else {
            playerZone = PlayerZone.valueOf("Green");
        }
        try {
            List<CardInGameInformation> hand = matchService.getPlayerCardsInHand(matchId, playerZone);
            return ResponseEntity.ok().body(hand);
        } catch (PlayerNotInGameException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{player}/played-cards")
    public ResponseEntity<List<CardInGameInformation>> getCardsInZones(@PathVariable String player) {
        int matchId = 1;
        if (!player.equals("blue") && !player.equals("green")) {
            return ResponseEntity.badRequest().build();
        }
        PlayerZone playerZone;
        if (player.equals("blue")) {
            playerZone = PlayerZone.valueOf("Blue");
        } else {
            playerZone = PlayerZone.valueOf("Green");
        }
        try {
            List<CardInGameInformation> hand = matchService.getCardsInActiveZones(matchId, playerZone);
            return ResponseEntity.ok().body(hand);
        } catch (PlayerNotInGameException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/summon")
    public ResponseEntity<String> summonCard(@RequestHeader("my-authorization") String token,
            @RequestBody SummonCard summonCard) {
        // String extractedMatchId = jwtUtil.extractUsername(token);
        // int matchId = Integer.parseInt(extractedMatchId);
        // if (matchId < 1 || matchId > matchService.totalGames) {
        // return ResponseEntity.badRequest().body("Error summoning card: match not
        // found");
        // }
        int matchId = 1;
        try {
            CardName cardName = CardName.valueOf(summonCard.cardName);
            ActiveZoneType zone = ActiveZoneType.valueOf(summonCard.zone);
            return ResponseEntity.ok()
                    .body(matchService.summonCard(matchId, cardName, zone));
        } catch (PlayerNotInGameException e) {
            return ResponseEntity.badRequest().body("Error summoning card: player not in game");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error summoning card: " + e);
        }
    }

    @PostMapping("/skip")
    public ResponseEntity<String> skipToPhase(@RequestBody SkipToPhase skipToPhase) {
        int matchId = 1;
        try {
            PlayerZone playerZone = PlayerZone.valueOf(skipToPhase.username);
            PhaseType phase = PhaseType.valueOf(skipToPhase.phase);
            return ResponseEntity.ok().body(matchService.skipToPhase(matchId, playerZone, phase));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error going to next phase: " + e);
        }
    }

    @PostMapping("/attack")
    public ResponseEntity<String> attackPlayer(@RequestBody CardAttack cardAttack) {
        int matchId = 1;
        try {
            CardName cardName = CardName.valueOf(cardAttack.cardName);
            return ResponseEntity.ok().body(matchService.attackPlayer(matchId, cardName, cardAttack.idx));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error attacking player: " + e);
        }
    }

    @PostMapping("/attack-creature")
    public ResponseEntity<String> attackCreature(@RequestBody CardAttack cardAttack) {
        int matchId = 1;
        try {
            CardName cardName = CardName.valueOf(cardAttack.cardName);
            CardName cardTarget = CardName.valueOf(cardAttack.cardNameTarget);
            return ResponseEntity.ok().body(matchService.attackCreature(matchId, cardName, cardAttack.idx, cardTarget));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error attacking creature: " + e);
        }
    }

    @PostMapping("/skip-reaction")
    public ResponseEntity<String> skipReaction() {
        int matchId = 1;
        try {
            return ResponseEntity.ok().body(matchService.skipReaction(matchId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error skipping reaction: " + e);
        }
    }

    @PostMapping("/activate-artifact")
    public ResponseEntity<String> activateArtifact(@RequestBody ActivateArtifact activateArtifact) {
        int matchId = 1;
        try {
            CardName cardName = CardName.valueOf(activateArtifact.actifact);
            return ResponseEntity.ok().body(matchService.activateArtifact(matchId, cardName,
                    activateArtifact.idx));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error activating artifact: " + e);
        }
    }

    @PostMapping("/activate-action")
    public ResponseEntity<String> activateAction(@RequestBody ActivateAction activateAction) {
        int matchId = 1;
        try {
            CardName cardName = CardName.valueOf(activateAction.action);
            return ResponseEntity.ok().body(matchService.activateAction(matchId, cardName,
                    activateAction.idx));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error activating action: " + e);
        }
    }
}

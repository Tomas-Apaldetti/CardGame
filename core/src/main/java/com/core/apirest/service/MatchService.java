package com.core.apirest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.apirest.model.CardInGameInformation;
import com.core.apirest.model.MatchInformation;
import com.core.apirest.model.PlayerMatchInformation;
import com.core.apirest.service.exceptions.MatchAlreadyStartedException;
import com.core.apirest.service.exceptions.PlayerNotInGameException;
import com.core.g3.Card.Card;
import com.core.g3.Card.CardFactory;
import com.core.g3.Card.CardName;
import com.core.g3.Deck.Deck;
import com.core.g3.Match.Match;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.GameMode.GameMode1;
import com.core.g3.Match.GameMode.GameMode2;
import com.core.g3.Match.Phase.PhaseType;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.User.User;

@Component
public class MatchService {
    public int totalGames = 0;
    public List<Match> matches = new ArrayList<>();
    @Autowired
    private UserService userService;

    public int createMatch(String bluePlayer, String greenPlayer, String gameMode) {
        GameMode newGameMode;
        if (gameMode.equals("HitPointLoss")) {
            newGameMode = new GameMode1();
        } else {
            newGameMode = new GameMode2();
        }

        User blueUser = userService.getUser(bluePlayer).user;
        User greeUser = userService.getUser(greenPlayer).user;

        // Create deck only for testing purposes
        Deck blueDeck = generateRandomDeck(40, 3);
        Deck greenDeck = generateRandomDeck(40, 3);
        // Create deck only for testing purposes

        Player newBluePlayer = newGameMode.addPlayer(blueUser, blueDeck);
        Player newGreenPlayer = newGameMode.addPlayer(greeUser, greenDeck);
        Match newMatch = new Match(newBluePlayer, newGreenPlayer, newGameMode);
        this.totalGames++;
        this.matches.add(newMatch);
        return this.totalGames;
    }

    public Match getMatch(int matchId) {
        return this.matches.get(matchId - 1);
    }

    public MatchInformation getMatchInformation(int matchId) {
        Match match = this.getMatch(matchId);
        MatchInformation matchInformation = MatchInformation.fromMatch(matchId, match);
        return matchInformation;
    }

    public MatchInformation startMatch(int matchId) {
        Match match = this.getMatch(matchId);
        if (!match.getPhaseType().equals(PhaseType.NotPlayable)) {
            throw new MatchAlreadyStartedException();
        }
        match.startMatch(PlayerZone.Blue);
        MatchInformation matchInformation = MatchInformation.fromMatch(matchId, match);
        return matchInformation;
    }

    public PlayerMatchInformation getPlayerMatchInformation(int matchId, String username) {
        Match match = this.getMatch(matchId);
        String bluePlayer = match.getPlayer(PlayerZone.Blue).getUsername();
        String greenPlayer = match.getPlayer(PlayerZone.Green).getUsername();
        if (!bluePlayer.equals(username) && !greenPlayer.equals(username)) {
            throw new PlayerNotInGameException();
        }

        System.out.println("username: " + username);
        System.out.println("bluePlayer: " + bluePlayer);
        System.out.println("greenPlayer: " + greenPlayer);
        Player player = match.getPlayer(PlayerZone.Blue).getUsername().equals(username)
                ? match.getPlayer(PlayerZone.Blue)
                : match.getPlayer(PlayerZone.Green);
        System.out.println("Viewing player: " + player.getUsername());
        PlayerMatchInformation playerMatchInformation = PlayerMatchInformation.fromPlayer(player);
        return playerMatchInformation;
    }

    public List<CardInGameInformation> getPlayerCardsInHand(int matchId, String username) {
        Match match = this.getMatch(matchId);
        String bluePlayer = match.getPlayer(PlayerZone.Blue).getUsername();
        String greenPlayer = match.getPlayer(PlayerZone.Green).getUsername();
        if (!bluePlayer.equals(username) && !greenPlayer.equals(username)) {
            throw new PlayerNotInGameException();
        }
        Player player = match.getPlayer(PlayerZone.Blue).getUsername().equals(username)
                ? match.getPlayer(PlayerZone.Blue)
                : match.getPlayer(PlayerZone.Green);
        List<CardInGameInformation> cards = player.seeHand().stream().map(card -> CardInGameInformation.fromCard(card))
                .collect(Collectors.toList());
        return cards;
    }

    public String summonCard(int matchId, String username, String cardName, String zone) {
        Match match = this.getMatch(matchId);
        String bluePlayer = match.getPlayer(PlayerZone.Blue).getUsername();
        String greenPlayer = match.getPlayer(PlayerZone.Green).getUsername();
        if (!bluePlayer.equals(username) && !greenPlayer.equals(username)) {
            throw new PlayerNotInGameException();
        }
        PlayerZone playerZone = match.getPlayer(PlayerZone.Blue).getUsername().equals(username)
                ? PlayerZone.Blue
                : PlayerZone.Green;

        ActiveZoneType zoneType = ActiveZoneType.valueOf(zone);
        match.summon(playerZone, CardName.valueOf(cardName), zoneType);
        return "Card summond";
    }

    public String skipToPhase(int matchId, String username, String phase) {
        Match match = this.getMatch(matchId);
        PlayerZone playerZone = match.getPlayer(PlayerZone.Blue).getUsername().equals(username)
                ? PlayerZone.Blue
                : PlayerZone.Green;
        PhaseType phaseType = PhaseType.valueOf(phase);
        match.skipToPhase(playerZone, phaseType);
        return "Phase skipped";
    }

    // Create deck only for testing purposes
    public Deck generateRandomDeck(int numberOfCards, int numberPerCard) {
        Deck deck = new Deck("test_deck");
        for (CardName cardName : CardName.values()) {
            for (int i = 0; i < numberPerCard; i++) {
                Card card = CardFactory.createCard(cardName);
                deck.addCard(card);
                if (deck.getCards().size() == numberOfCards) {
                    return deck;
                }
            }
        }
        return deck;
    }
    // Create deck only for testing purposes
}

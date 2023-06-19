package com.core.apirest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        System.out.println(blueDeck.getCards().size());
        System.out.println(greenDeck.getCards().size());
        // Create deck only for testing purposes

        Player newBluePlayer = newGameMode.addPlayer(blueUser, blueDeck);
        Player newGreenPlayer = newGameMode.addPlayer(greeUser, greenDeck);
        Match newMatch = new Match(newBluePlayer, newGreenPlayer, newGameMode);
        this.totalGames++;
        this.matches.add(newMatch);
        return this.totalGames;
    }

    public String getMatch(int matchId) {
        return this.matches.get(matchId - 1).toString();
    }

    public MatchInformation getMatchInformation(int matchId) {
        Match match = this.matches.get(matchId - 1);
        MatchInformation matchInformation = MatchInformation.fromMatch(matchId, match);
        return matchInformation;
    }

    public MatchInformation startMatch(int matchId) {
        Match match = this.matches.get(matchId - 1);
        if (!match.getPhaseType().equals(PhaseType.NotPlayable)) {
            throw new MatchAlreadyStartedException();
        }
        match.startMatch(PlayerZone.Blue);
        MatchInformation matchInformation = MatchInformation.fromMatch(matchId, match);
        return matchInformation;
    }

    public PlayerMatchInformation getPlayerMatchInformation(int matchId, String username) {
        Match match = this.matches.get(matchId - 1);
        String bluePlayer = match.getPlayer(PlayerZone.Blue).getUsername();
        String greenPlayer = match.getPlayer(PlayerZone.Green).getUsername();
        if (!bluePlayer.equals(username) && !greenPlayer.equals(username)) {
            throw new PlayerNotInGameException();
        }
        Player player = match.getPlayer(PlayerZone.Blue).getUsername() == username
                ? match.getPlayer(PlayerZone.Blue)
                : match.getPlayer(PlayerZone.Green);
        PlayerMatchInformation playerMatchInformation = PlayerMatchInformation.fromPlayer(player);
        return playerMatchInformation;
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

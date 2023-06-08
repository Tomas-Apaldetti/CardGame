package com.core.g3.Match.GameMode;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.Player.MatchEndCondition.PointsCounter;
import com.core.g3.Match.Player.Player;
import com.core.g3.User.User;

import java.util.Optional;

public class GameMode2 extends GameMode {
    private int winnerPoints;

    public GameMode2() {
        this.combatZoneLimit = 1;
        this.artifactZoneLimit = 5;
        this.reserveZoneLimit = 3;
        this.initialHandSize = 7;
        this.MAXIMUM_CARDS_PER_DECK = 60;
        this.MINIMUM_CARDS_PER_DECK = 60;
        this.MAXIMUM_REPEATED_CARDS = 4;
        this.initialPoints = 0;
        this.winnerPoints = 6;
    }

    public Player addPlayer(User user, IDeck deck) {
        this.checkDecks(deck);
        this.checkRepeatedCards(deck, MAXIMUM_REPEATED_CARDS);

        DeckPlayable playableDeck = new DeckPlayable(deck);
        Player player = new Player(user, playableDeck,
                new PointsCounter(new Amount(this.initialPoints), new Amount(this.winnerPoints)));
        return player;
    }

    public int getWinnerPoints() {
        return this.winnerPoints;
    }

    public void drawInitialCards(Player player) {
        for (int i = 0; i < initialHandSize; i++) {
            player.drawCard();
        }
    }

    public void initialStage(Player player) {
        player.drawCard();
    }

    public Optional<Player> getWinner(Player player1, Player player2) {
        if (player1.matchEndConditionMet()) {
            return Optional.of(player2);
        } else if (player2.matchEndConditionMet()) {
            return Optional.of(player1);
        }
        return Optional.empty();
    }
}
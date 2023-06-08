package com.core.g3.Match.GameMode;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.Match.Player.Player;
import com.core.g3.User.User;

import java.util.Optional;

public class GameMode1 extends GameMode {

    public GameMode1() {
        this.combatZoneLimit = 5;
        this.artifactZoneLimit = 0;
        this.reserveZoneLimit = 5;
        this.initialHandSize = 5;
        this.MAXIMUM_CARDS_PER_DECK = 60;
        this.MINIMUM_CARDS_PER_DECK = 40;
        this.MAXIMUM_REPEATED_CARDS = 3;
        this.initialPoints = 20;
    }

    public Player addPlayer(User user, IDeck deck) {
        this.checkDecks(deck);
        this.checkRepeatedCards(deck, MAXIMUM_REPEATED_CARDS);

        DeckPlayable playableDeck = new DeckPlayable(deck);
        Player player = new Player(user, playableDeck, new PlainHP(new Amount(this.initialPoints)));
        return player;
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
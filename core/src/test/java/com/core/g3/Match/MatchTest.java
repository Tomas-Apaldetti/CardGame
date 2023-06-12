package com.core.g3.Match;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.GameMode.GameMode1;
import com.core.g3.Match.GameMode.GameMode2;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.User.User;

public class MatchTest {

    @Test
    public void checkWinnerGameMode1() {
        Player playerBlue = this.createPlayer("blue", "test1", 100);
        Player playerGreen = this.createPlayer("green", "test2", 0);
        GameMode1 gameMode = new GameMode1();

        Match match = new Match(playerBlue, playerGreen, gameMode);

        Optional<PlayerZone> playerZoneBlue = Optional.of(PlayerZone.Blue);
        assertEquals(playerZoneBlue, match.getWinner());
    }

    @Test
    public void checkWinnerGameMode2() {
        Player playerBlue = this.createPlayer("blue", "test1", 100);
        Player playerGreen = this.createPlayer("green", "test2", 0);
        GameMode2 gameMode = new GameMode2();

        Match match = new Match(playerBlue, playerGreen, gameMode);

        Optional<PlayerZone> playerZoneGreen = Optional.of(PlayerZone.Green);
        assertEquals(playerZoneGreen, match.getWinner());
    }
    
    public Player createPlayer(String username, String deckName, Integer initialAmount) {
        User user = new User(username);
        Deck deck = new Deck(deckName);
        DeckPlayable playableDeck = new DeckPlayable(deck);
        PlainHP condition = new PlainHP(new Amount(initialAmount));
        return new Player(user, playableDeck, condition, null, null, null);
    }
}

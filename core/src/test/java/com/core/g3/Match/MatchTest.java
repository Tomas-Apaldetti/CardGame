package com.core.g3.Match;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.GameMode.GameMode1;
import com.core.g3.Match.GameMode.GameMode2;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.User.User;

public class MatchTest {

    @Test
    public void checkWinnerGameMode1() {
        GameMode1 gameMode = new GameMode1();
        Deck basicDeck = generateRandomDeck(40, 3);
        Player playerBlue = gameMode.addPlayer(new User("blue"), basicDeck);
        Player playerGreen = gameMode.addPlayer(new User("green"), basicDeck);

        Match match = new Match(playerBlue, playerGreen, gameMode);
        playerGreen.affectMatchEndCondition(new Amount(20));
        Optional<PlayerZone> playerZoneBlue = Optional.of(PlayerZone.Blue);
        assertEquals(playerZoneBlue, match.getWinner());
    }

    @Test
    public void checkWinnerGameMode2() {
        GameMode2 gameMode = new GameMode2();
        Deck basicDeck = generateRandomDeck(60, 4);
        Player playerBlue = gameMode.addPlayer(new User("blue"), basicDeck);
        Player playerGreen = gameMode.addPlayer(new User("green"), basicDeck);

        Match match = new Match(playerBlue, playerGreen, gameMode);
        playerGreen.affectMatchEndCondition(new Amount(6));
        Optional<PlayerZone> playerZoneGreen = Optional.of(PlayerZone.Green);
        assertEquals(playerZoneGreen, match.getWinner());
    }

    @Test
    public void checkStartGame() {
        GameMode1 gameMode1 = new GameMode1();
        Deck deck = generateRandomDeck(40, 3);
        Player playerBlue = gameMode1.addPlayer(new User("blue"), deck);
        Player playerGreen = gameMode1.addPlayer(new User("green"), deck);

        Match match = new Match(playerBlue, playerGreen, gameMode1);
        match.startMatch(PlayerZone.Blue);

        assertEquals(5, playerBlue.seeHand().size());
        assertEquals(5, playerGreen.seeHand().size());
    }

    // @Test
    // public void checkSummonCard() {
    // GameMode1 gameMode1 = new GameMode1();
    // Deck basicDeck = generateRandomDeck(40, 3);
    // Player playerBlue = gameMode1.addPlayer(new User("blue"), basicDeck);
    // Player playerGreen = gameMode1.addPlayer(new User("green"), basicDeck);

    // Match match = new Match(playerBlue, playerGreen, gameMode1);
    // match.startMatch(PlayerZone.Blue);

    // ICard card = playerBlue.seeHand().get(0);

    // match.summon(PlayerZone.Blue, card, ActiveZoneType.Combat);

    // assertEquals(1,
    // playerBlue.seeActiveZone(ActiveZoneType.Combat).currentCardCount());
    // }

    public Deck generateRandomDeck(int numberOfCards, int numberPerCard) {
        Deck deck = new Deck("test_deck");
        HashMap<CardName, Integer> cards = new HashMap<CardName, Integer>();
        for (int i = 0; i < numberOfCards; i++) {
            // Add a random card from CardName only if it is not already 3 in the deck
            CardName randomCard = CardName.values()[(int) (Math.random() * CardName.values().length)];
            if (cards.containsKey(randomCard)) {
                if (cards.get(randomCard) < numberPerCard) {
                    cards.put(randomCard, cards.get(randomCard) + 1);
                    deck.addCard(new Card(randomCard, true));
                } else {
                    i--;
                }
            } else {
                cards.put(randomCard, 1);
                deck.addCard(new Card(randomCard, true));
            }
        }
        return deck;
    }
}

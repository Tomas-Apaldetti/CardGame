package com.core.g3.Match.GameMode;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Match.GameMode.Exceptions.InvalidDeckCount;
import com.core.g3.Match.Player.Player;
import com.core.g3.User.User;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class GameMode1Test {

    @Test
    public void NewGameMode() {
        GameMode1 gameMode1 = new GameMode1();
        assert (gameMode1 != null);
    }

    @Test
    public void addPlayerWithEmptyDeckToGameMode() {
        GameMode1 gameMode1 = new GameMode1();
        // Should rise an exception because the deck is empty
        assertThrows(InvalidDeckCount.class, () -> {
            gameMode1.addPlayer(new User("test"), new Deck("test_deck"));
        });
    }

    @Test
    public void addPlayerWithValidDeckToGameMode() {
        GameMode1 gameMode1 = new GameMode1();
        Deck deck = generateRandomDeck(40, 3);
        Player player = gameMode1.addPlayer(new User("test"), deck);
        assert (player != null);
    }

    @Test
    public void addPlayerWithInvalidDeckToGameMode() {
        GameMode1 gameMode1 = new GameMode1();
        Deck deck = generateRandomDeck(40, 4);
        assertThrows(InvalidDeckCount.class, () -> {
            gameMode1.addPlayer(new User("test"), deck);
        });
    }

    @Test
    public void addPlayerWithTooMuchCardsDeckToGameMode() {
        GameMode1 gameMode1 = new GameMode1();
        Deck deck = generateRandomDeck(57, 3);
        deck.addCard(new Card(CardName.Corrosion, false));
        deck.addCard(new Card(CardName.Corrosion, false));
        deck.addCard(new Card(CardName.Corrosion, false));
        deck.addCard(new Card(CardName.Corrosion, false));
        assertThrows(InvalidDeckCount.class, () -> {
            gameMode1.addPlayer(new User("test"), deck);
        });
    }

    @Test
    public void checkInitialStage() {
        GameMode1 gameMode1 = new GameMode1();
        Deck deck = generateRandomDeck(40, 3);
        Player player = gameMode1.addPlayer(new User("test"), deck);
        // Before draw initial cards
        int beforeHandSize = player.seeHand().size();
        assertEquals(0, beforeHandSize);
        // After draw initial cards
        gameMode1.drawInitialCards(player);
        int afterHandSize = player.seeHand().size();
        // Print afterHandSize
        assertEquals(gameMode1.getInitialHandSize(), afterHandSize);
    }

    @Test
    public void checkIfThereIsAWinner() {
        GameMode1 gameMode1 = new GameMode1();
        Deck deck = generateRandomDeck(40, 3);
        Player player1 = gameMode1.addPlayer(new User("test"), deck);
        Player player2 = gameMode1.addPlayer(new User("test"), deck);
        // Check if there is a winner
        assertFalse(gameMode1.getWinner(player1, player2).isPresent());
    }

    @Test
    public void checkWinnerIsPlayerOne() {
        GameMode1 gameMode1 = new GameMode1();
        Deck deck = generateRandomDeck(40, 3);
        Player player1 = gameMode1.addPlayer(new User("test"), deck);
        Player player2 = gameMode1.addPlayer(new User("test"), deck);
        player2.affectMatchEndCondition(new Amount(gameMode1.getInitialPoints()));
        assertTrue(gameMode1.getWinner(player1, player2).isPresent());
        assertTrue(gameMode1.getWinner(player1, player2).get().equals(player1));
    }

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
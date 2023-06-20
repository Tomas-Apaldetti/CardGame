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

class GameMode2Test {

    @Test
    public void NewGameMode() {
        GameMode2 GameMode2 = new GameMode2();
        assert (GameMode2 != null);
    }

    @Test
    public void addPlayerWithEmptyDeckToGameMode() {
        GameMode2 GameMode2 = new GameMode2();
        // Should rise an exception because the deck is empty
        assertThrows(InvalidDeckCount.class, () -> {
            GameMode2.addPlayer(new User("test"), new Deck("test_deck"));
        });
    }

    @Test
    public void addPlayerWithValidDeckToGameMode() {
        GameMode2 GameMode2 = new GameMode2();
        Deck deck = generateRandomDeck(60, 4);
        Player player = GameMode2.addPlayer(new User("test"), deck);
        assert (player != null);
    }

    @Test
    public void addPlayerWithInvalidDeckToGameMode() {
        GameMode2 GameMode2 = new GameMode2();
        Deck deck = generateRandomDeck(12, 4);
        assertThrows(InvalidDeckCount.class, () -> {
            GameMode2.addPlayer(new User("test"), deck);
        });
    }

    @Test
    public void addPlayerWithTooMuchCardsDeckToGameMode() {
        GameMode2 GameMode2 = new GameMode2();
        Deck deck = generateRandomDeck(61, 4);
        deck.addCard(new Card(CardName.Corrosion, false));
        deck.addCard(new Card(CardName.Corrosion, false));
        deck.addCard(new Card(CardName.Corrosion, false));
        deck.addCard(new Card(CardName.Corrosion, false));
        assertThrows(InvalidDeckCount.class, () -> {
            GameMode2.addPlayer(new User("test"), deck);
        });
    }

    @Test
    public void checkInitialStage() {
        GameMode2 GameMode2 = new GameMode2();
        Deck deck = generateRandomDeck(60, 4);
        Player player = GameMode2.addPlayer(new User("test"), deck);
        // Before draw initial cards
        int beforeHandSize = player.seeHand().size();
        assertEquals(0, beforeHandSize);
        // After draw initial cards
        GameMode2.drawInitialCards(player);
        int afterHandSize = player.seeHand().size();
        // Print afterHandSize
        assertEquals(7, afterHandSize);
    }

    @Test
    public void checkIfThereIsAWinner() {
        GameMode2 GameMode2 = new GameMode2();
        Deck deck = generateRandomDeck(60, 4);
        Player player1 = GameMode2.addPlayer(new User("test"), deck);
        Player player2 = GameMode2.addPlayer(new User("test"), deck);
        // Check if there is a winner
        assertFalse(GameMode2.getWinner(player1, player2).isPresent());
    }

    @Test
    public void checkWinnerIsPlayerOne() {
        GameMode2 GameMode2 = new GameMode2();
        Deck deck = generateRandomDeck(60, 4);
        Player player1 = GameMode2.addPlayer(new User("test"), deck);
        Player player2 = GameMode2.addPlayer(new User("test"), deck);
        player2.affectMatchEndCondition(new Amount(7));
        assertTrue(GameMode2.getWinner(player1, player2).isPresent());
        assertTrue(GameMode2.getWinner(player1, player2).get().equals(player2));
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
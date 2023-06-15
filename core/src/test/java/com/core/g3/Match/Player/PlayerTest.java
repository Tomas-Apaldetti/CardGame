package com.core.g3.Match.Player;

import com.core.g3.Card.Card;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.DeckPlayable.EmptyDeckPlayableException;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.User.User;

import org.junit.jupiter.api.Test;
import com.core.g3.Card.CardName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Optional;

class PlayerTest {
    @Test
    public void createPlayerIsOk() {
        Player player = createPlayer();
        assertNotNull(player);
    }

    @Test
    public void checkPlayerInitialEnergies() {
        Player player = createPlayer();
        player.getEnergies().forEach(e -> assertEquals(0, e.available()));
        assertEquals(0, player.getEnergy(EnergyType.Fire).available());
        assertEquals(0, player.getEnergy(EnergyType.Plant).available());
        assertEquals(0, player.getEnergy(EnergyType.Water).available());
    }

    @Test
    public void checkPlayerAddEnergies() {
        Player player = createPlayer();
        player.add(EnergyType.Fire, new Amount(10));
        player.add(EnergyType.Plant, new Amount(20));
        player.add(EnergyType.Water, new Amount(30));
        assertEquals(10, player.getEnergy(EnergyType.Fire).available());
        assertEquals(20, player.getEnergy(EnergyType.Plant).available());
        assertEquals(30, player.getEnergy(EnergyType.Water).available());
    }

    @Test
    public void checkPlayerAddEnergiesTwice() {
        Player player = createPlayer();
        player.add(EnergyType.Fire, new Amount(10));
        player.add(EnergyType.Plant, new Amount(20));
        player.add(EnergyType.Water, new Amount(30));
        player.add(EnergyType.Fire, new Amount(10));
        player.add(EnergyType.Plant, new Amount(20));
        player.add(EnergyType.Water, new Amount(30));
        assertEquals(20, player.getEnergy(EnergyType.Fire).available());
        assertEquals(40, player.getEnergy(EnergyType.Plant).available());
        assertEquals(60, player.getEnergy(EnergyType.Water).available());
    }

    @Test
    public void checkConsumePlayerEnergies() {
        Player player = createPlayer();
        player.add(EnergyType.Fire, new Amount(10));
        player.add(EnergyType.Plant, new Amount(20));
        player.add(EnergyType.Water, new Amount(30));
        player.consume(Optional.of(EnergyType.Fire), new Amount(5));
        player.consume(Optional.of(EnergyType.Plant), new Amount(10));
        player.consume(Optional.of(EnergyType.Water), new Amount(15));
        assertEquals(5, player.getEnergy(EnergyType.Fire).available());
        assertEquals(10, player.getEnergy(EnergyType.Plant).available());
        assertEquals(15, player.getEnergy(EnergyType.Water).available());
    }

    @Test
    public void checkConsumeAnyPlayerEnergies() {
        Player player = createPlayer();
        player.add(EnergyType.Fire, new Amount(10));
        player.consume(Optional.empty(), new Amount(5));
        assertEquals(5, player.getEnergy(EnergyType.Fire).available());
    }

    @Test
    public void checkPlayerEmptyDeck() {
        Player player = createPlayer();
        // This should raise an exception
        assertThrows(EmptyDeckPlayableException.class, () -> {
            player.drawCard();
        });
    }

    @Test
    public void checkDrawCardFromDeck() {
        Player player = getPlayerWithDeck(40, 3);
        player.drawCard();
        assertEquals(1, player.seeHand().size());
    }

    public Player createPlayer() {
        User user = new User("test");
        Deck deck = new Deck("test");
        DeckPlayable playableDeck = new DeckPlayable(deck);
        PlainHP condition = new PlainHP(new Amount(100));
        return new Player(user, playableDeck, condition, null, null, null);
    }

    public Player getPlayerWithDeck(int numberOfCards, int numberPerCard) {
        User user = new User("test");
        Deck deck = generateRandomDeck(numberOfCards, numberPerCard);
        DeckPlayable playableDeck = new DeckPlayable(deck);
        PlainHP condition = new PlainHP(new Amount(100));
        return new Player(user, playableDeck, condition, null, null, null);
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
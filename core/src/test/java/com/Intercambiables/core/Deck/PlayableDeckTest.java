package com.Intercambiables.core.Deck;

import org.junit.jupiter.api.Test;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Card.CardName;
import com.Intercambiables.core.Match.DeckPlayable.DeckPlayable;
import com.Intercambiables.core.Match.DeckPlayable.EmptyDeckPlayableException;
import com.Intercambiables.core.Match.DeckPlayable.IDeckPlayable;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayableDeckTest {

    @Test
    public void createPlayableDeck() {
        Deck deck = new Deck("mazo_1");
        DeckPlayable playableDeck = new DeckPlayable(deck);

        assertEquals(true, playableDeck instanceof IDeckPlayable);
    }

    @Test
    public void getCardFromPlayableDeck() {
        Deck deck = new Deck("mazo_1");
        // add a card to the deck
        var card = new Card(CardName.Corrosion, false);
        deck.addCard(card);
        DeckPlayable playableDeck = new DeckPlayable(deck);

        assertEquals(card, playableDeck.getCard());
    }

    @Test
    public void getCardsFromPlayableDeck() {
        Deck deck = new Deck("mazo_1");
        ICard card1 = new Card(CardName.Corrosion, false);
        ICard card2 = new Card(CardName.Alquimista, false);
        deck.addCard(card1);
        deck.addCard(card2);

        ArrayList<ICard> cardsStub = new ArrayList<ICard>();
        cardsStub.add(card1);
        cardsStub.add(card2);

        DeckPlayable playableDeck = new DeckPlayable(deck);

        ArrayList<ICard> cards = new ArrayList<ICard>();
        cards.add(playableDeck.getCard());
        cards.add(playableDeck.getCard());

        assertEquals(true, cardsStub.containsAll(cards));
    }

    @Test
    public void getMoreCardsFromPlayableDeckThanAdded() {
        Deck deck = new Deck("mazo_1");
        ICard card1 = new Card(CardName.Corrosion, false);
        ICard card2 = new Card(CardName.Alquimista, false);
        deck.addCard(card1);
        deck.addCard(card2);

        ArrayList<ICard> cardsStub = new ArrayList<ICard>();
        cardsStub.add(card1);
        cardsStub.add(card2);

        DeckPlayable playableDeck = new DeckPlayable(deck);

        ArrayList<ICard> cards = new ArrayList<ICard>();
        cards.add(playableDeck.getCard());
        cards.add(playableDeck.getCard());

        assertThrows(EmptyDeckPlayableException.class, () -> playableDeck.getCard());
        assertEquals(true, cardsStub.containsAll(cards));
    }
}

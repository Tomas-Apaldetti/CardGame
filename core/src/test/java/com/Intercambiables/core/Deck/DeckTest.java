package com.Intercambiables.core.Deck;

import org.junit.jupiter.api.Test;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Card.CardType;
import com.Intercambiables.core.Deck.Exceptions.CardAlreadyExistsInDeckException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class DeckTest {

    @Test
    public void addSingleCard() {
        Deck deck = new Deck("mazo_1");

        ICard card = new Card(CardType.Corrosion, false);

        deck.addCard(card);

        assertEquals(deck.getCards().contains(card), true);
        assertEquals(deck.getCards().size(), 1);
    }

    @Test
    public void addTwoCards() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion = new Card(CardType.Corrosion, false);
        ICard alquimista = new Card(CardType.Drenar, false);

        deck.addCard(corrosion);
        deck.addCard(alquimista);

        ArrayList<ICard> cards = new ArrayList<ICard>();

        cards.add(corrosion);
        cards.add(alquimista);

        assertEquals(true, deck.getCards().containsAll(cards));
        assertEquals(2, deck.getCards().size());
    }

    @Test
    public void addMultipleCardsByArray() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion = new Card(CardType.Corrosion, false);
        ICard alquimista = new Card(CardType.Drenar, false);

        ArrayList<ICard> cards = new ArrayList<ICard>();

        cards.add(corrosion);
        cards.add(alquimista);

        deck.addCards(cards);

        assertEquals(true, deck.getCards().containsAll(cards));
        assertEquals(2, deck.getCards().size());
    }

    @Test
    public void addDuplicatedCardsThrows() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion = new Card(CardType.Corrosion, false);

        deck.addCard(corrosion);

        assertThrows(CardAlreadyExistsInDeckException.class, () -> deck.addCard(corrosion));
        assertEquals(1, deck.getCards().size());
    }

    @Test
    public void addSameTypeCards() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion1 = new Card(CardType.Corrosion, false);
        ICard corrosion2 = new Card(CardType.Corrosion, false);

        deck.addCard(corrosion1);
        deck.addCard(corrosion2);

        ArrayList<ICard> cards = new ArrayList<ICard>();

        cards.add(corrosion1);
        cards.add(corrosion2);

        assertEquals(true, deck.getCards().containsAll(cards));
        assertEquals(2, deck.getCards().size());
    }

    @Test
    public void removeCardOnePending() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion = new Card(CardType.Corrosion, false);
        ICard alquimista = new Card(CardType.Drenar, false);

        deck.addCard(corrosion);
        deck.addCard(alquimista);

        deck.removeCard(corrosion);

        assertEquals(true, deck.getCards().contains(alquimista));
        assertEquals(1, deck.getCards().size());
    }

    @Test
    public void removeCardNonExistent() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion = new Card(CardType.Corrosion, false);
        ICard alquimista = new Card(CardType.Drenar, false);

        deck.addCard(alquimista);

        deck.removeCard(corrosion);

        assertEquals(true, deck.getCards().contains(alquimista));
        assertEquals(1, deck.getCards().size());
    }

    @Test
    public void removeCardEmptyDeckItsStillBeingEmpy() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion = new Card(CardType.Corrosion, false);

        deck.removeCard(corrosion);

        assertEquals(false, deck.getCards().contains(corrosion));
        assertEquals(0, deck.getCards().size());
    }

    @Test
    public void deckWithSameTypeCardsCanRemoveSingleOne() {
        Deck deck = new Deck("mazo_1");

        ICard corrosion1 = new Card(CardType.Corrosion, false);
        ICard corrosion2 = new Card(CardType.Corrosion, false);

        deck.addCard(corrosion1);
        deck.addCard(corrosion2);

        ArrayList<ICard> cards = new ArrayList<ICard>();

        cards.add(corrosion1);

        deck.removeCard(corrosion2);

        assertEquals(true, deck.getCards().containsAll(cards));
        assertEquals(1, deck.getCards().size());
    }
}

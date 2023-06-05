package com.core.g3.Match.CardContainer;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardType;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardContainer.Exception.CardAlreadyOnPlaceException;
import com.core.g3.Match.CardContainer.Exception.CardNotOnPlaceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardContainerTest {

    @Test
    public void handContainsCorrectCards() {
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardType.Alquimista, false);
        Card card2 = new Card(CardType.Alquimista, false);

        cardContainer.add(card1);
        cardContainer.add(card2);

        List<ICard> cards = cardContainer.peek();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void handRemovesCorrectCard() {
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardType.Alquimista, false);
        Card card2 = new Card(CardType.Alquimista, false);

        cardContainer.add(card1);
        cardContainer.add(card2);

        ICard removed = cardContainer.remove(card1);
        List<ICard> cards = cardContainer.peek();
        assertEquals(1, cards.size());
        assertEquals(card1, removed);
        assertTrue(cards.contains(card2));
        assertFalse(cards.contains(card1));
    }

    @Test
    public void handRemoveCardNotOnHandThrows() {
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardType.Alquimista, false);
        Card card2 = new Card(CardType.Alquimista, false);
        Card fake = new Card(CardType.Alquimista, false);

        cardContainer.add(card1);
        cardContainer.add(card2);

        assertThrows(CardNotOnPlaceException.class, () -> cardContainer.remove(fake));
        List<ICard> cards = cardContainer.peek();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card2));
        assertTrue(cards.contains(card1));
    }

    @Test
    public void handAddDuplicatedThrows() {
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardType.Alquimista, false);

        cardContainer.add(card1);
        assertThrows(CardAlreadyOnPlaceException.class, () -> cardContainer.add(card1));

    }
}
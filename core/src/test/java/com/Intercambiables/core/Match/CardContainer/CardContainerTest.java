package com.Intercambiables.core.Match.CardContainer;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Card.CardName;
import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Match.CardContainer.Exception.CardAlreadyOnPlaceException;
import com.Intercambiables.core.Match.CardContainer.Exception.CardNotOnPlaceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardContainerTest {

    @Test
    public void handContainsCorrectCards(){
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardName.Alquimista, false);
        Card card2 = new Card(CardName.Alquimista, false);

        cardContainer.add(card1);
        cardContainer.add(card2);

        List<ICard> cards= cardContainer.peek();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void handRemovesCorrectCard(){
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardName.Alquimista, false);
        Card card2 = new Card(CardName.Alquimista, false);

        cardContainer.add(card1);
        cardContainer.add(card2);

        ICard removed = cardContainer.remove(card1);
        List<ICard> cards= cardContainer.peek();
        assertEquals(1, cards.size());
        assertEquals(card1, removed);
        assertTrue(cards.contains(card2));
        assertFalse(cards.contains(card1));
    }

    @Test
    public void handRemoveCardNotOnHandThrows(){
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardName.Alquimista, false);
        Card card2 = new Card(CardName.Alquimista, false);
        Card fake = new Card(CardName.Alquimista, false);

        cardContainer.add(card1);
        cardContainer.add(card2);

        assertThrows(CardNotOnPlaceException.class, ()-> cardContainer.remove(fake));
        List<ICard> cards= cardContainer.peek();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card2));
        assertTrue(cards.contains(card1));
    }

    @Test
    public void handAddDuplicatedThrows(){
        CardContainer cardContainer = new CardContainer();
        Card card1 = new Card(CardName.Alquimista, false);

        cardContainer.add(card1);
        assertThrows(CardAlreadyOnPlaceException.class, ()-> cardContainer.add(card1));

    }
}
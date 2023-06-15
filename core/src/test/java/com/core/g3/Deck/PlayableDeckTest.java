package com.core.g3.Deck;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.DeckPlayable.IDeckPlayable;

import java.util.ArrayList;
import java.util.List;

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
        Card card = new Card(CardName.Corrosion, false);
        deck.addCard(card);
        DeckPlayable playableDeck = new DeckPlayable(deck);

        assertEquals(card, playableDeck.getCard());
    }

    @Test
    public void getCardsFromPlayableDeck() {
        Deck deck = new Deck("mazo_1");
        ICard card1 = new Card(CardName.Corrosion, false);
        ICard card2 = new Card(CardName.Alchemist, false);
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
        ICard card2 = new Card(CardName.Alchemist, false);
        deck.addCard(card1);
        deck.addCard(card2);

        ArrayList<ICard> cardsStub = new ArrayList<ICard>();
        cardsStub.add(card1);
        cardsStub.add(card2);

        DeckPlayable playableDeck = new DeckPlayable(deck);

        ArrayList<ICard> cards = new ArrayList<ICard>();
        cards.add(playableDeck.getCard());
        cards.add(playableDeck.getCard());

        // assertThrows(EmptyDeckPlayableException.class, () -> playableDeck.getCard());
        assertEquals(true, cardsStub.containsAll(cards));
    }

    @Test
    public void forceTestOrder() {
        Deck deck = new Deck("mazo_1");
        List<ICard> cards = new ArrayList<ICard>();
        cards.add(new Card(CardName.Corrosion, false));
        cards.add(new Card(CardName.Alchemist, false));
        cards.add(new Card(CardName.Treason, false));
        cards.add(new Card(CardName.MagicBarrier, false));
        deck.addCards(cards);

        DeckPlayable playableDeck = new DeckPlayable(deck);
        List<CardName> newOrder = new ArrayList<CardName>() {
            {
                add(CardName.Treason);
                add(CardName.Corrosion);
                add(CardName.Alchemist);
                add(CardName.MagicBarrier);
            }
        };

        playableDeck.forceOrder(newOrder);
        assertEquals(newOrder, playableDeck.getCards());
    }

    @Test
    public void forceTestOrderWithRepeatedCards() {
        Deck deck = new Deck("mazo_1");
        List<ICard> cards = new ArrayList<ICard>();
        cards.add(new Card(CardName.Corrosion, false));
        cards.add(new Card(CardName.Alchemist, false));
        cards.add(new Card(CardName.Treason, false));
        cards.add(new Card(CardName.MagicBarrier, false));
        cards.add(new Card(CardName.MagicBarrier, false));
        cards.add(new Card(CardName.MagicBarrier, false));
        deck.addCards(cards);

        DeckPlayable playableDeck = new DeckPlayable(deck);
        List<CardName> newOrder = new ArrayList<CardName>() {
            {
                add(CardName.MagicBarrier);
                add(CardName.Treason);
                add(CardName.Corrosion);
                add(CardName.MagicBarrier);
                add(CardName.Alchemist);
                add(CardName.MagicBarrier);
            }
        };

        playableDeck.forceOrder(newOrder);

        for (int i = 0; i < newOrder.size(); i++) {
            assertEquals(newOrder.get(i), playableDeck.getCards().get(i));
        }
        assertEquals(newOrder, playableDeck.getCards());
    }
}

package com.core.g3.Deck;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardType;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.DeckPlayable.IDeckPlayable;

import java.util.ArrayList;

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
                Card card = new Card(CardType.Corrosion, false);
                deck.addCard(card);
                DeckPlayable playableDeck = new DeckPlayable(deck);

                assertEquals(card, playableDeck.getCard());
        }

        @Test
        public void getCardsFromPlayableDeck() {
                Deck deck = new Deck("mazo_1");
                ICard card1 = new Card(CardType.Corrosion, false);
                ICard card2 = new Card(CardType.Alchemist, false);
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
                ICard card1 = new Card(CardType.Corrosion, false);
                ICard card2 = new Card(CardType.Alchemist, false);
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
}

package com.core.g3.Deck;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Deck.Exceptions.CardAlreadyExistsInDeckException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class DeckTest {

        @Test
        public void addSingleCard() {
                Deck deck = new Deck("mazo_1");

                ICard card = new Card(CardName.Corrosion, false);

                deck.addCard(card);

                assertEquals(true, deck.getCards().contains(card));
                assertEquals(1, deck.getCards().size());
        }

        @Test
        public void addTwoCards() {
                Deck deck = new Deck("mazo_1");

                ICard corrosion = new Card(CardName.Corrosion, false);
                ICard alchemist = new Card(CardName.Drain, false);

                deck.addCard(corrosion);
                deck.addCard(alchemist);

                ArrayList<ICard> cards = new ArrayList<ICard>();

                cards.add(corrosion);
                cards.add(alchemist);

                assertEquals(true, deck.getCards().containsAll(cards));
                assertEquals(2, deck.getCards().size());
        }

        @Test
        public void addMultipleCardsByArray() {
                Deck deck = new Deck("mazo_1");

                ICard corrosion = new Card(CardName.Corrosion, false);
                ICard alchemist = new Card(CardName.Drain, false);

                ArrayList<ICard> cards = new ArrayList<ICard>();

                cards.add(corrosion);
                cards.add(alchemist);

                deck.addCards(cards);

                assertEquals(true, deck.getCards().containsAll(cards));
                assertEquals(2, deck.getCards().size());
        }

        @Test
        public void addDuplicatedCardsThrows() {
                Deck deck = new Deck("mazo_1");

                ICard corrosion = new Card(CardName.Corrosion, false);

                deck.addCard(corrosion);

                assertThrows(CardAlreadyExistsInDeckException.class, () -> deck.addCard(corrosion));
                assertEquals(1, deck.getCards().size());
        }

        @Test
        public void addSameTypeCards() {
                Deck deck = new Deck("mazo_1");

                ICard corrosion1 = new Card(CardName.Corrosion, false);
                ICard corrosion2 = new Card(CardName.Corrosion, false);

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

                ICard corrosion = new Card(CardName.Corrosion, false);
                ICard alchemist = new Card(CardName.Drain, false);

                deck.addCard(corrosion);
                deck.addCard(alchemist);

                deck.removeCard(corrosion);

                assertEquals(true, deck.getCards().contains(alchemist));
                assertEquals(1, deck.getCards().size());
        }

        @Test
        public void removeCardNonExistent() {
                Deck deck = new Deck("mazo_1");

                ICard corrosion = new Card(CardName.Corrosion, false);
                ICard alchemist = new Card(CardName.Drain, false);

                deck.addCard(alchemist);

                deck.removeCard(corrosion);

                assertEquals(true, deck.getCards().contains(alchemist));
                assertEquals(1, deck.getCards().size());
        }

        @Test
        public void removeCardEmptyDeckItsStillBeingEmpy() {
                Deck deck = new Deck("mazo_1");

                ICard corrosion = new Card(CardName.Corrosion, false);

                deck.removeCard(corrosion);

                assertEquals(false, deck.getCards().contains(corrosion));
                assertEquals(0, deck.getCards().size());
        }

        @Test
        public void deckWithSameTypeCardsCanRemoveSingleOne() {
                Deck deck = new Deck("mazo_1");

                ICard corrosion1 = new Card(CardName.Corrosion, false);
                ICard corrosion2 = new Card(CardName.Corrosion, false);

                deck.addCard(corrosion1);
                deck.addCard(corrosion2);

                ArrayList<ICard> cards = new ArrayList<ICard>();

                cards.add(corrosion1);

                deck.removeCard(corrosion2);

                assertEquals(true, deck.getCards().containsAll(cards));
                assertEquals(1, deck.getCards().size());
        }
}

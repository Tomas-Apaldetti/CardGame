package com.core.g3.User;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardType;
import com.core.g3.Deck.ICard;
import com.core.g3.User.Exceptions.DuplicatedCardReferenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class CardInventoryTest {

        @Test
        public void createCardInventory() {
                CardInventory cardInventory = new CardInventory();

                ICard card = new Card(CardType.Corrosion, false);

                cardInventory.addCard(card);

                assertEquals(true, cardInventory.getCards().contains(card));
                assertEquals(1, cardInventory.getCards().size());
        }

        @Test
        public void removeCard() {
                CardInventory cardInventory = new CardInventory();

                ICard card = new Card(CardType.Corrosion, false);

                cardInventory.addCard(card);

                cardInventory.removeCard(card);

                assertEquals(false, cardInventory.getCards().contains(card));
                assertEquals(0, cardInventory.getCards().size());
        }

        @Test
        public void addDuplicatedCardThrows() {
                CardInventory cardInventory = new CardInventory();

                ICard card = new Card(CardType.Corrosion, false);

                cardInventory.addCard(card);

                assertThrows(DuplicatedCardReferenceException.class, () -> cardInventory.addCard(card));
                assertEquals(1, cardInventory.getCards().size());
        }

        @Test
        public void addeMultipleCards() {
                CardInventory cardInventory = new CardInventory();

                ICard corrosion = new Card(CardType.Corrosion, false);
                ICard alquimista = new Card(CardType.Alquimista, false);
                ICard antimagia1 = new Card(CardType.Antimagia, true);
                ICard barreramagica = new Card(CardType.BarreraMagica, false);
                ICard antimagia2 = new Card(CardType.Antimagia, true);

                cardInventory.addCard(corrosion);
                cardInventory.addCard(alquimista);
                cardInventory.addCard(antimagia1);
                cardInventory.addCard(barreramagica);
                cardInventory.addCard(antimagia2);

                ArrayList<ICard> cards = new ArrayList<ICard>();

                cards.add(corrosion);
                cards.add(alquimista);
                cards.add(antimagia1);
                cards.add(barreramagica);
                cards.add(antimagia2);

                assertEquals(true, cardInventory.getCards().containsAll(cards));
                assertEquals(5, cardInventory.getCards().size());
        }
}

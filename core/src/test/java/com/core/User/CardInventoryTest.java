package com.core.User;

import org.junit.jupiter.api.Test;

import com.core.Card.Card;
import com.core.Card.CardName;
import com.core.Deck.ICard;
import com.core.User.Exceptions.DuplicatedCardReferenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class CardInventoryTest {

        @Test
        public void createCardInventory() {
                CardInventory cardInventory = new CardInventory();

                ICard card = new Card(CardName.Corrosion, false);

                cardInventory.addCard(card);

                assertEquals(true, cardInventory.getCards().contains(card));
                assertEquals(1, cardInventory.getCards().size());
        }

        @Test
        public void removeCard() {
                CardInventory cardInventory = new CardInventory();

                ICard card = new Card(CardName.Corrosion, false);

                cardInventory.addCard(card);

                cardInventory.removeCard(card);

                assertEquals(false, cardInventory.getCards().contains(card));
                assertEquals(0, cardInventory.getCards().size());
        }

        @Test
        public void addDuplicatedCardThrows() {
                CardInventory cardInventory = new CardInventory();

                ICard card = new Card(CardName.Corrosion, false);

                cardInventory.addCard(card);

                assertThrows(DuplicatedCardReferenceException.class, () -> cardInventory.addCard(card));
                assertEquals(1, cardInventory.getCards().size());
        }

        @Test
        public void addeMultipleCards() {
                CardInventory cardInventory = new CardInventory();

                ICard corrosion = new Card(CardName.Corrosion, false);
                ICard alquimista = new Card(CardName.Alquimista, false);
                ICard antimagia1 = new Card(CardName.Antimagia, true);
                ICard barreramagica = new Card(CardName.BarreraMagica, false);
                ICard antimagia2 = new Card(CardName.Antimagia, true);

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

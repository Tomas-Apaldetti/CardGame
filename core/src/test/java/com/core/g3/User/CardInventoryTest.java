package com.core.g3.User;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.User.Exceptions.DuplicatedCardReferenceException;

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
                ICard alchemist = new Card(CardName.Alchemist, false);
                ICard antimagic1 = new Card(CardName.Antimagic, true);
                ICard magicbarrier = new Card(CardName.MagicBarrier, false);
                ICard antimagic2 = new Card(CardName.Antimagic, true);

                cardInventory.addCard(corrosion);
                cardInventory.addCard(alchemist);
                cardInventory.addCard(antimagic1);
                cardInventory.addCard(magicbarrier);
                cardInventory.addCard(antimagic2);

                ArrayList<ICard> cards = new ArrayList<ICard>();

                cards.add(corrosion);
                cards.add(alchemist);
                cards.add(antimagic1);
                cards.add(magicbarrier);
                cards.add(antimagic2);

                assertEquals(true, cardInventory.getCards().containsAll(cards));
                assertEquals(5, cardInventory.getCards().size());
        }
}

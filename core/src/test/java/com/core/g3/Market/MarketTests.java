package com.core.g3.Market;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardType;
import com.core.g3.Commons.Amount;
import com.core.g3.Market.Exceptions.NotEnoughFoundsException;
import com.core.g3.Market.Exceptions.PublisherIsBuyerException;
import com.core.g3.Market.Transactions.ITransaction;
import com.core.g3.Market.Transactions.Status.TransactionStatus;
import com.core.g3.User.TestUserRegister;
import com.core.g3.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarketTests {
        @Test
        public void marketCreatesCorrectly() {
                Market mk = new Market();
                assertEquals(0, mk.retrievePublications().size());
        }

        @Test
        public void marketPublishTransactionCorrectly() {
                User seller = TestUserRegister.createUser("pepe", "pepe");
                Card card = new Card(CardType.Alchemist, true);
                card.addTo(seller);
                Market mk = new Market();
                mk.publishTransaction(seller, card, new Amount(10));

                assertEquals(1, mk.retrievePublications().size());
        }

        @Test
        public void marketDoTransactionCorrectly() {
                User seller = TestUserRegister.createUser("pepe", "pepe");
                User buyer = TestUserRegister.createUser("jose", "jose");
                Card card = new Card(CardType.Alchemist, true);
                buyer.credit(new Amount(10));
                card.addTo(seller);
                Market mk = new Market();
                ITransaction transaction = mk.publishTransaction(seller, card, new Amount(10));

                assertEquals(1, mk.retrievePublications().size());
                assertEquals(TransactionStatus.TRANSACTION_APPLIED, mk.doTransaction(transaction, buyer));
                assertEquals(0, mk.retrievePublications().size());
        }

        @Test
        public void marketDoTransactionCorrectlyForNotEnough() {
                User seller = TestUserRegister.createUser("pepe", "pepe");
                User buyer = TestUserRegister.createUser("jose", "jose");
                Card card = new Card(CardType.Alchemist, true);
                buyer.credit(new Amount(5));
                card.addTo(seller);
                Market mk = new Market();
                ITransaction transaction = mk.publishTransaction(seller, card, new Amount(10));

                assertEquals(1, mk.retrievePublications().size());
                assertThrows(NotEnoughFoundsException.class, () -> mk.doTransaction(transaction, buyer));
                assertEquals(1, mk.retrievePublications().size());
        }

        @Test
        public void marketWithPublisherEqualsBuyerThrows() {
                User seller = TestUserRegister.createUser("pepe", "pepe");
                User buyer = TestUserRegister.createUser("jose", "jose");
                Card card = new Card(CardType.Alchemist, true);
                buyer.credit(new Amount(5));
                card.addTo(seller);
                Market mk = new Market();
                ITransaction transaction = mk.publishTransaction(seller, card, new Amount(10));

                assertEquals(1, mk.retrievePublications().size());
                assertThrows(PublisherIsBuyerException.class, () -> mk.doTransaction(transaction, seller));
                assertEquals(1, mk.retrievePublications().size());
        }
}

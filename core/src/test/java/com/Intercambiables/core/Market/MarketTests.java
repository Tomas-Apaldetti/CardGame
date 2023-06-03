package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Card.CardType;
import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Market.Exception.NotEnoughFoundsException;
import com.Intercambiables.core.Market.Exception.PublisherIsBuyerException;
import com.Intercambiables.core.Market.Transactions.ITransaction;
import com.Intercambiables.core.Market.Transactions.Status.TransactionStatus;
import com.Intercambiables.core.User.TestUserRegister;
import com.Intercambiables.core.User.User;
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
        Card card = new Card(CardType.Alquimista, true);
        card.addTo(seller);
        Market mk = new Market();
        mk.publishTransaction(seller, card, new Amount(10));

        assertEquals(1, mk.retrievePublications().size());
    }

    @Test
    public void marketDoTransactionCorrectly() {
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        Card card = new Card(CardType.Alquimista, true);
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
        Card card = new Card(CardType.Alquimista, true);
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
        Card card = new Card(CardType.Alquimista, true);
        buyer.credit(new Amount(5));
        card.addTo(seller);
        Market mk = new Market();
        ITransaction transaction = mk.publishTransaction(seller, card, new Amount(10));

        assertEquals(1, mk.retrievePublications().size());
        assertThrows(PublisherIsBuyerException.class, () -> mk.doTransaction(transaction, seller));
        assertEquals(1, mk.retrievePublications().size());
    }
}

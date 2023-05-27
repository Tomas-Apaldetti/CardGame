package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.User.TestUserRegister;
import com.Intercambiables.core.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MarketTests {
    @Test
    public void marketCreatesCorrectly(){
        Market mk = new Market();
        assertEquals(0,mk.retrieveCardPublications().size());
    }

    @Test
    public void marketPublishTransactionCorrectly(){
        User seller = TestUserRegister.createUser("pepe", "pepe");
        Card card = new Card("cartita");
        card.addTo(seller);
        Market mk = new Market();
        mk.publishTransaction(seller, card, new Amount(10));

        assertEquals(1,mk.retrieveCardPublications().size());
    }

    @Test
    public void marketDoTransactionCorrectly(){
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        Card card = new Card("cartita");
        buyer.credit(new Amount(10));
        card.addTo(seller);
        Market mk = new Market();
        ITransaction transaction = mk.publishTransaction(seller, card, new Amount(10));

        assertEquals(0,buyer.getCards().size());
        assertEquals(1,seller.getCards().size());
        assertEquals(1,mk.retrieveCardPublications().size());
        assertEquals(TransactionStatus.TRANSACTION_APPLIED,mk.doTransaction(transaction,buyer));
        assertEquals(0,mk.retrieveCardPublications().size());
        assertEquals(1,buyer.getCards().size());
        assertEquals(0,seller.getCards().size());
    }
    @Test
    public void marketDoTransactionCorrectlyForNotEnough(){
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        Card card = new Card("cartita");
        buyer.credit(new Amount(5));
        card.addTo(seller);
        Market mk = new Market();
        ITransaction transaction = mk.publishTransaction(seller, card, new Amount(10));

        assertEquals(0,buyer.getCards().size());
        assertEquals(1,seller.getCards().size());
        assertEquals(1,mk.retrieveCardPublications().size());
        assertEquals(TransactionStatus.NOT_ENOUGH_FUNDS,mk.doTransaction(transaction,buyer));
        assertEquals(1,mk.retrieveCardPublications().size());
        assertEquals(0,buyer.getCards().size());
        assertEquals(1,seller.getCards().size());
    }
}

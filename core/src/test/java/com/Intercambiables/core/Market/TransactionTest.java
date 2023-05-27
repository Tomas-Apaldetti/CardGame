package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.User.TestUserRegister;
import com.Intercambiables.core.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void transactionCreditsTheCorrectAmount(){
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        ITransactionable card = new Card("cartita");
        buyer.credit(new Amount(10));
        card.addTo(seller);

        ITransaction trans = TestTransactionFactory.createTransaction(seller,new Amount(10),card);
        TransactionStatus status = trans.apply(buyer);

        assertEquals(card, buyer.getCards().get(0));
        assertEquals(0, seller.getCards().size());
        assertEquals(0, buyer.getFounds());
        assertEquals(10, seller.getFounds());
        assertEquals(TransactionStatus.TRANSACTION_APPLIED, status);
    }

    @Test
    public void transactionNot(){
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        ITransactionable card = new Card("cartita");
        buyer.credit(new Amount(5));
        card.addTo(seller);

        ITransaction trans = TestTransactionFactory.createTransaction(seller,new Amount(10),card);
        TransactionStatus status = trans.apply(buyer);

        assertEquals(card, seller.getCards().get(0));
        assertEquals(0, buyer.getCards().size());
        assertEquals(0, seller.getFounds());
        assertEquals(5, buyer.getFounds());
        assertEquals(TransactionStatus.NOT_ENOUGH_FUNDS, status);
    }
}

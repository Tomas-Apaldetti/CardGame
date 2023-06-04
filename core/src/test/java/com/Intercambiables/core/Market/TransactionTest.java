package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.driver.DriverCardName;
import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Market.Exception.NotEnoughFoundsException;
import com.Intercambiables.core.Market.Exception.TransactionAlreadyAppliedException;
import com.Intercambiables.core.Market.Transactions.ITransaction;
import com.Intercambiables.core.Market.Transactions.ITransactionable;
import com.Intercambiables.core.Market.Transactions.Status.TransactionStatus;
import com.Intercambiables.core.User.TestUserRegister;
import com.Intercambiables.core.User.User;
import com.Intercambiables.core.Commons.Amount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void transactionCreditsTheCorrectAmount() {
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        ITransactionable card = new Card(DriverCardName.Alchemist, true);
        buyer.credit(new Amount(10));
        card.addTo(seller);

        ITransaction trans = TestTransactionFactory.createTransaction(seller, new Amount(10), card);
        TransactionStatus status = trans.apply(buyer);

        assertEquals(true, (buyer.getCards()).contains((ICard) card));
        assertEquals(0, seller.getCards().size());
        assertEquals(0, buyer.getFounds());
        assertEquals(10, seller.getFounds());
        assertEquals(TransactionStatus.TRANSACTION_APPLIED, status);
    }

    @Test
    public void transactionNotEnoughFounds() {
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        ITransactionable card = new Card(DriverCardName.Alchemist, true);
        buyer.credit(new Amount(5));
        card.addTo(seller);

        ITransaction trans = TestTransactionFactory.createTransaction(seller, new Amount(10), card);
        assertThrows(NotEnoughFoundsException.class, () -> trans.apply(buyer));

        assertEquals(false, (buyer.getCards()).contains((ICard) card));
        assertEquals(0, buyer.getCards().size());
        assertEquals(0, seller.getFounds());
        assertEquals(5, buyer.getFounds());
    }

    @Test
    public void transactionDoubleApplyThrows() {
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        ITransactionable card = new Card(DriverCardName.Alchemist, true);
        buyer.credit(new Amount(20));
        card.addTo(seller);

        ITransaction trans = TestTransactionFactory.createTransaction(seller, new Amount(1), card);
        assertDoesNotThrow(() -> trans.apply(buyer));
        assertThrows(TransactionAlreadyAppliedException.class, () -> trans.apply(buyer));

    }

    @Test
    public void transactionRecognizesPublisher() {
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        ITransactionable card = new Card(DriverCardName.Alchemist, true);

        ITransaction trans = TestTransactionFactory.createTransaction(seller, new Amount(1), card);

        assertEquals(true, trans.isPublisher(seller));
        assertEquals(false, trans.isPublisher(buyer));
    }
}

package com.core.g3.Market;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.Exception.NotEnoughFoundsException;
import com.core.g3.Market.Exception.TransactionAlreadyAppliedException;
import com.core.g3.Market.Transactions.ITransaction;
import com.core.g3.Market.Transactions.ITransactionable;
import com.core.g3.Market.Transactions.Status.TransactionStatus;
import com.core.g3.User.TestUserRegister;
import com.core.g3.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

        @Test
        public void transactionCreditsTheCorrectAmount() {
                User seller = TestUserRegister.createUser("pepe", "pepe");
                User buyer = TestUserRegister.createUser("jose", "jose");
                ITransactionable card = new Card(CardName.Alquimista, true);
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
                ITransactionable card = new Card(CardName.Alquimista, true);
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
                ITransactionable card = new Card(CardName.Alquimista, true);
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
                ITransactionable card = new Card(CardName.Alquimista, true);

                ITransaction trans = TestTransactionFactory.createTransaction(seller, new Amount(1), card);

                assertEquals(true, trans.isPublisher(seller));
                assertEquals(false, trans.isPublisher(buyer));
        }
}

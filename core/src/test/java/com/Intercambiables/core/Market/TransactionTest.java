package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.User.TestUserRegister;
import com.Intercambiables.core.User.User;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    public void transactionCreditsTheCorrectAmount(){
        User seller = TestUserRegister.createUser("pepe", "pepe");
        User buyer = TestUserRegister.createUser("jose", "jose");
        ITransactionable card = new Card("cartita");
        buyer.credit(new Amount(10));
        card.addTo(seller);

        ITransaction trans = TestTransactionFactory.createTransaction(seller,new Amount(10),card);
        trans.apply(buyer);


    }
}

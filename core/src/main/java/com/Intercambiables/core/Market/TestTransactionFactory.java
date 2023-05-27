package com.Intercambiables.core.Market;

import com.Intercambiables.core.User.User;

public class TestTransactionFactory {

    public static Transaction createTransaction(User publisher, Amount value, ITransactionable sellItem){
        return new Transaction(publisher,value,sellItem);
    }
}

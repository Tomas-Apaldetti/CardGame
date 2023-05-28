package com.Intercambiables.core.Market;

import com.Intercambiables.core.Market.Transactions.ISeller;
import com.Intercambiables.core.Market.Transactions.ITransactionable;

public class TestTransactionFactory {

    public static Transaction createTransaction(ISeller publisher, Amount value, ITransactionable sellItem){
        return new Transaction(publisher,value,sellItem);
    }
}

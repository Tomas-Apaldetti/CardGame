package com.Intercambiables.core.Market;

public class TestTransactionFactory {

    public static Transaction createTransaction(ISeller publisher, Amount value, ITransactionable sellItem){
        return new Transaction(publisher,value,sellItem);
    }
}

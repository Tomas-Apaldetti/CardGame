package com.core.Market;

import com.core.Commons.Amount;
import com.core.Market.Transactions.ISeller;
import com.core.Market.Transactions.ITransactionable;

public class TestTransactionFactory {

    public static Transaction createTransaction(ISeller publisher, Amount value, ITransactionable sellItem) {
        return new Transaction(publisher, value, sellItem);
    }
}

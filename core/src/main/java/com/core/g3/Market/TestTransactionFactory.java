package com.core.g3.Market;

import com.core.g3.Commons.Amount;
import com.core.g3.Market.Transactions.ISeller;
import com.core.g3.Market.Transactions.ITransactionable;

public class TestTransactionFactory {

    public static Transaction createTransaction(ISeller publisher, Amount value, ITransactionable sellItem) {
        return new Transaction(publisher, value, sellItem);
    }
}

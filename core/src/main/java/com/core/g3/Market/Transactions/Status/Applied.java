package com.core.g3.Market.Transactions.Status;

import com.core.g3.Market.Exception.TransactionAlreadyAppliedException;

public class Applied implements ITransactionStatus {
    @Override
    public void assertCanApply() {
        throw new TransactionAlreadyAppliedException();
    }

    @Override
    public ITransactionStatus next() {
        return this;
    }

    @Override
    public TransactionStatus type() {
        return TransactionStatus.TRANSACTION_APPLIED;
    }
}

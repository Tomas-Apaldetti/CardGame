package com.core.g3.Market.Transactions.Status;

public class Pending implements ITransactionStatus {

    @Override
    public void assertCanApply() {
        return;
    }

    @Override
    public ITransactionStatus next() {
        return new Applied();
    }

    @Override
    public TransactionStatus type() {
        return TransactionStatus.PENDING;
    }
}

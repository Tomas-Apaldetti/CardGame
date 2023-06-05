package com.core.Market.Transactions.Status;

public interface ITransactionStatus {

    void assertCanApply();

    ITransactionStatus next();

    TransactionStatus type();
}

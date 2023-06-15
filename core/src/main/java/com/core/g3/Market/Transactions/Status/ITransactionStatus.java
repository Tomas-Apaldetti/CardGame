package com.core.g3.Market.Transactions.Status;

public interface ITransactionStatus {

    void assertCanApply();

    ITransactionStatus next();

    TransactionStatus type();
}

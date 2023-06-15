package com.core.g3.Market.Transactions;

import com.core.g3.Market.Transactions.Status.TransactionStatus;

public interface ITransaction {

     TransactionStatus apply(IBuyer user);

     TransactionStatus status();

     boolean isPublisher(IBuyer user);
}

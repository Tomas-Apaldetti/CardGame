package com.core.Market.Transactions;

import com.core.Market.Transactions.Status.TransactionStatus;

public interface ITransaction {

     TransactionStatus apply(IBuyer user);

     TransactionStatus status();

     boolean isPublisher(IBuyer user);
}

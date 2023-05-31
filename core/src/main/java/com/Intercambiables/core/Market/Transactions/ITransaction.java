package com.Intercambiables.core.Market.Transactions;

import com.Intercambiables.core.Market.Transactions.Status.TransactionStatus;

public interface ITransaction {

     TransactionStatus apply(IBuyer user);

     TransactionStatus status();

     boolean isPublisher(IBuyer user);
}

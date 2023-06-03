package com.Intercambiables.core.Market;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Market.Exception.PublisherIsBuyerException;
import com.Intercambiables.core.Market.Transactions.IBuyer;
import com.Intercambiables.core.Market.Transactions.ISeller;
import com.Intercambiables.core.Market.Transactions.ITransaction;
import com.Intercambiables.core.Market.Transactions.ITransactionable;
import com.Intercambiables.core.Market.Transactions.Status.TransactionStatus;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Market {
    private HashMap<TransactionStatus, Collection<ITransaction>> publications;

    Market() {
        this.publications = new HashMap<>();
        this.publications.put(TransactionStatus.PENDING,new HashSet<>());
        this.publications.put(TransactionStatus.TRANSACTION_APPLIED,new HashSet<>());
        this.publications.put(TransactionStatus.CLOSED,new HashSet<>());
    }
    public Collection<ITransaction> retrievePublications(){
        return publications.get(TransactionStatus.PENDING);
    }

    public ITransaction publishTransaction(ISeller publisher, ITransactionable card, Amount price){
        ITransaction transaction = new Transaction(publisher, price, card);
        this.publications.get(TransactionStatus.PENDING).add(transaction);
        return transaction;
    }

    public TransactionStatus doTransaction(ITransaction tran, IBuyer interested) {
        this.assertDifferentEntity(tran, interested);
        Collection<ITransaction> prev = this.publications.get(tran.status());
        TransactionStatus status = tran.apply(interested);
        this.moveTransaction(prev, tran);
        return status;
    }

    private void moveTransaction(Collection<ITransaction> currentIn, ITransaction transaction) {
        currentIn.remove(transaction);
        this.publications.get(transaction.status()).add(transaction);
    }

    private void assertDifferentEntity(ITransaction tran, IBuyer interested){
        if (tran.isPublisher(interested)){
            throw new PublisherIsBuyerException();
        }
    }
}

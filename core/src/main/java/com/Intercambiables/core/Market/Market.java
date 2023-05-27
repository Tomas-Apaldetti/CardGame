package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.User.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Market {
    private HashMap<TransactionStatus,Collection<ITransaction>> publications;

    Market() {
        this.publications = new HashMap<>();
        this.publications.put(TransactionStatus.PENDING,new HashSet<>());
        this.publications.put(TransactionStatus.TRANSACTION_APPLIED,new HashSet<>());
        this.publications.put(TransactionStatus.CLOSED,new HashSet<>());
    }
    public Collection<ITransaction> retrieveCardPublications(){
        return publications.get(TransactionStatus.PENDING);
    }

    public ITransaction publishTransaction(User publisher, Card card, Amount price){
        ITransaction transaction = new Transaction(publisher, price, card);
        this.publications.get(TransactionStatus.PENDING).add(transaction);
        return transaction;
    }

    public TransactionStatus doTransaction(ITransaction tran, User interested) {
        TransactionStatus status = tran.apply(interested);
        if (status == TransactionStatus.TRANSACTION_APPLIED) {
            this.publications.get(TransactionStatus.PENDING).remove(tran);
            this.publications.get(TransactionStatus.TRANSACTION_APPLIED).add(tran);
        }
        return status;
    }
}

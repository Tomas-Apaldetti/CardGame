package com.Intercambiables.core.Market;

import com.Intercambiables.core.Market.Exception.NotEnoughFoundsException;
import com.Intercambiables.core.Market.Transactions.IBuyer;
import com.Intercambiables.core.Market.Transactions.ISeller;
import com.Intercambiables.core.Market.Transactions.ITransaction;
import com.Intercambiables.core.Market.Transactions.ITransactionable;
import com.Intercambiables.core.Market.Transactions.Status.ITransactionStatus;
import com.Intercambiables.core.Market.Transactions.Status.Pending;
import com.Intercambiables.core.Market.Transactions.Status.TransactionStatus;

public class Transaction implements ITransaction {

    private final ISeller publisher;
    private final Amount value;
    private final ITransactionable item;
    private ITransactionStatus status;

    Transaction(ISeller publisher, Amount value, ITransactionable sellItem){
        this.publisher = publisher;
        this.value = value;
        this.item = sellItem;
        this.status = new Pending();
    }

    Transaction(ISeller publisher, Amount value, ITransactionable sellItem, ITransactionStatus initialStatus){
        this.publisher = publisher;
        this.value = value;
        this.item = sellItem;
        this.status = initialStatus;
    }
    
    @Override
    public TransactionStatus apply(IBuyer buyer) {
        this.status.assertCanApply();
        if (!buyer.hasEnoughFounds(this.value)){
            throw new NotEnoughFoundsException();
        }

        this.transferItem(buyer);
        this.transferMoney(buyer);
        this.status = this.status.next();

        return this.status.type();
    }

    @Override
    public TransactionStatus status() {
        return this.status.type();
    }

    @Override
    public boolean isPublisher(IBuyer buyer){
        return this.publisher == buyer;
    }

    private void transferItem(IBuyer buyer){
        this.item.removeFrom(this.publisher);
        this.item.addTo(buyer);
    }

    private void transferMoney(IBuyer buyer){
        this.publisher.credit(this.value);
        buyer.subtract(this.value);
    }


}

package com.Intercambiables.core.Market;

import com.Intercambiables.core.User.User;

public class Transaction implements ITransaction{

    private final User publisher;
    private final Amount value;
    private final ITransactionable item;
    private TransactionStatus status;

    Transaction(User publisher, Amount value, ITransactionable sellItem){
        this.publisher = publisher;
        this.value = value;
        this.item = sellItem;
        this.status = TransactionStatus.PENDING;
    }
    
    @Override
    public TransactionStatus apply(User buyer) {
        if (!buyer.hasEnoughFounds(this.value)){
            return TransactionStatus.NOT_ENOUGH_FUNDS;
        } else {
            this.transferItem(buyer);
            this.transferMoney(buyer);
            this.status = TransactionStatus.TRANSACTION_APPLIED;
        }
        return this.status;
    }

    private void transferItem(User buyer){
        this.item.removeFrom(this.publisher);
        this.item.addTo(buyer);
    }

    private void transferMoney(User buyer){
        this.publisher.credit(this.value);
        buyer.subtract(this.value);
    }
}

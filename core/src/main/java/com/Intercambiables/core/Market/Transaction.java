package com.Intercambiables.core.Market;

import com.Intercambiables.core.User.User;

public class Transaction implements ITransaction{

    private final ISeller publisher;
    private final Amount value;
    private final ITransactionable item;

    Transaction(ISeller publisher, Amount value, ITransactionable sellItem){
        this.publisher = publisher;
        this.value = value;
        this.item = sellItem;
    }
    
    @Override
    public TransactionStatus apply(IBuyer buyer) {
        if (buyer.hasEnoughFounds(this.value)){
            return TransactionStatus.NOT_ENOUGH_FUNDS;
        };

        this.transferItem(buyer);
        this.transferMoney(buyer);

        return TransactionStatus.TRANSACTION_APPLIED;
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

package com.Intercambiables.core.Card;

import com.Intercambiables.core.Market.Transactions.IBuyer;
import com.Intercambiables.core.Market.Transactions.ISeller;
import com.Intercambiables.core.Market.Transactions.ITransactionable;

public class Card implements ITransactionable {

    public String name;
    public Card(String name){
        this.name = name;
    }

    @Override
    public void removeFrom(ISeller seller){
        seller.removeItem(this);
    }

    @Override
    public void addTo(IBuyer buyer) {
        buyer.addItem(this);
    }
}

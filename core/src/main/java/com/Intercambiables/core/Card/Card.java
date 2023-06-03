package com.Intercambiables.core.Card;

import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Market.Transactions.IBuyer;
import com.Intercambiables.core.Market.Transactions.ISeller;
import com.Intercambiables.core.Market.Transactions.ITransactionable;

public class Card implements ITransactionable, ICard {

    private final CardName type;
    private final boolean shouldCount;

    public Card(CardName type, boolean shouldCount) {
        this.type = type;
        this.shouldCount = shouldCount;
    }

    @Override
    public void removeFrom(ISeller seller) {
        seller.removeItem(this);
    }

    @Override
    public void addTo(IBuyer buyer) {
        buyer.addItem(this);
    }

    @Override
    public CardName getType() {
        return this.type;
    }

    @Override
    public boolean shouldCountAgainstTypeLimit() {
        return this.shouldCount;
    }
}
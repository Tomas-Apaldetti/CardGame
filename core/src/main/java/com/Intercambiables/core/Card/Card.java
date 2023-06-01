package com.Intercambiables.core.Card;

import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Market.Transactions.IBuyer;
import com.Intercambiables.core.Market.Transactions.ISeller;
import com.Intercambiables.core.Market.Transactions.ITransactionable;

public class Card implements ITransactionable, ICard {

    private final CardType type;
    private final boolean shouldCount;

    public Card(CardType type, boolean shouldCount) {
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
    public CardType getType() {
        return this.type;
    }

    @Override
    public boolean shouldCountAgainstTypeLimit() {
        return this.shouldCount;
    }
}
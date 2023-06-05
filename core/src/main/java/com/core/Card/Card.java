package com.core.Card;

import com.core.Card.Cost.ICost;
import com.core.Card.Cost.NullInvocationCost;
import com.core.Deck.ICard;
import com.core.Market.Transactions.IBuyer;
import com.core.Market.Transactions.ISeller;
import com.core.Market.Transactions.ITransactionable;

public class Card implements ITransactionable, ICard {

    private final CardName type;
    private final boolean shouldCount;
    private final ICost invocationCost; // TODO -> remove?

    public Card(CardName type, boolean shouldCount) {
        this(type, shouldCount, new NullInvocationCost());
    }

    public Card(CardName type, boolean shouldCount, ICost invocationCost) {
        this.type = type;
        this.shouldCount = shouldCount;
        this.invocationCost = invocationCost;
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
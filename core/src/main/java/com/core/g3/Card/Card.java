package com.core.g3.Card;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.Transactions.IBuyer;
import com.core.g3.Market.Transactions.ISeller;
import com.core.g3.Market.Transactions.ITransactionable;

public class Card implements ITransactionable, ICard {

    private final CardName Name;
    private final boolean shouldCount;
    private final ICost invocationCost; // TODO -> remove?

    public Card(CardName name, boolean shouldCount) {
        this(name, shouldCount, new NullInvocationCost());
    }

    public Card(CardName name, boolean shouldCount, ICost invocationCost) {
        this.Name = name;
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
    public CardName getName() {
        return this.Name;
    }

    @Override
    public boolean shouldCountAgainstNameLimit() {
        return this.shouldCount;
    }
}
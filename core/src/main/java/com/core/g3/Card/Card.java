package com.core.g3.Card;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.Transactions.IBuyer;
import com.core.g3.Market.Transactions.ISeller;
import com.core.g3.Market.Transactions.ITransactionable;

public class Card implements ITransactionable, ICard {

    private final CardType type;
    private final boolean shouldCount;
    private final ICost invocationCost; // TODO -> remove?

    public Card(CardType type, boolean shouldCount) {
        this(type, shouldCount, new NullInvocationCost());
    }

    public Card(CardType type, boolean shouldCount, ICost invocationCost) {
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
    public CardType getType() {
        return this.type;
    }

    @Override
    public boolean shouldCountAgainstTypeLimit() {
        return this.shouldCount;
    }
}
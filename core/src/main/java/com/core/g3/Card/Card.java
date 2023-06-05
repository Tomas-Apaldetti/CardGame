package com.core.g3.Card;

import java.util.List;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.Transactions.IBuyer;
import com.core.g3.Market.Transactions.ISeller;
import com.core.g3.Market.Transactions.ITransactionable;

public class Card implements ITransactionable, ICard {

    private final CardName name;
    private final boolean shouldCount;
    private final ICost invocationCost; // TODO -> remove?
    private List<ICardType.CardType> cardTypes;

    public Card(CardName name, boolean shouldCount) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = new NullInvocationCost(); // TODO -> review
    }

    public Card(CardName name, boolean shouldCount, ICost invocationCost, List<ICardType.CardType> cardTypes) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = invocationCost;
        this.cardTypes = cardTypes;
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
        return this.name;
    }

    @Override
    public boolean shouldCountAgainstTypeLimit() {
        return this.shouldCount;
    }

    public List<ICardType.CardType> getTypes() {
        return this.cardTypes;
    }
}
package com.core.g3.Card;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Card.Type.Exceptions.CardTypeNoSummonableInZoneException;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.Transactions.IBuyer;
import com.core.g3.Market.Transactions.ISeller;
import com.core.g3.Market.Transactions.ITransactionable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZoneType;

public class Card implements ITransactionable, ICard {

    private final CardName name;
    private final boolean shouldCount;
    private final ICost invocationCost; // TODO -> remove?
    private List<CardType> cardTypes;
    private Amount price;
    private Amount summonableSpace;

    public Card(CardName name, boolean shouldCount) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = new NullInvocationCost(); // TODO -> review
        this.price = new Amount(0);
        this.summonableSpace = new Amount(0);
    }

    public Card(CardName name, boolean shouldCount, ICost invocationCost, List<CardType> cardTypes,
            Amount summonableSpace) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = invocationCost;
        this.cardTypes = cardTypes;
        this.price = new Amount(0);
        this.summonableSpace = summonableSpace;
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
    public boolean shouldCountAgainstNameLimit() {
        return this.shouldCount;
    }

    @Override
    public int getPrice() {
        return this.price.value();
    }

    public List<ICardType.CardType> getTypes() {
        ArrayList<ICardType.CardType> types = new ArrayList<ICardType.CardType>();

        this.cardTypes.forEach(cardType -> types.add(cardType.getType()));

        return types;
    }

    @Override
    public Amount summonIn(ActiveZoneType zoneType) {
        for (CardType cardType : this.cardTypes) {
            if (cardType.isSummonableIn(zoneType)) {
                return this.summonableSpace;
            }
        }
        throw new CardTypeNoSummonableInZoneException();
    }

    @Override
    public void applySummonCost(Player player) {
        this.invocationCost.apply(player);
    }
}
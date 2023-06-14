package com.core.g3.Card;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
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
    private final ICost invocationCost;
    private List<ICardType> cardTypes;
    private Amount price;
    private Amount summonableSpace;

    public Card(CardName name, boolean shouldCount) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = new NullInvocationCost();
        this.price = new Amount(0);
        this.summonableSpace = new Amount(0);
    }

    public Card(CardName name, boolean shouldCount, ICost invocationCost, List<ICardType> cardTypes,
            Amount summonableSpace, Amount price) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = invocationCost;
        this.cardTypes = cardTypes;
        this.price = price;
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

    public List<ICardType> getTypes() {
        return this.cardTypes;
    }

    @Override
    public Amount summonIn(ActiveZoneType zoneType) {
        for (ICardType cardType : this.cardTypes) {
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

    public List<ActiveZoneType> getAllowableZones() {
        // Create a empty list of activeZoneType
        List<ActiveZoneType> allowableZones = new ArrayList<>();
        for (ICardType cardType : this.cardTypes) {
            allowableZones.addAll(cardType.getAllowableZones());
        }
        return allowableZones;
    }
}
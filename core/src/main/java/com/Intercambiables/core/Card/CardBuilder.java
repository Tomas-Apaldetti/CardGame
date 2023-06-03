package com.Intercambiables.core.Card;

import com.Intercambiables.core.Card.Cost.ICost;

public class CardBuilder {

    private CardName cardName;
    private ICost invokeCost;

    public CardBuilder(CardName cardName) {
        this.cardName = cardName;
    }

    public Card build() {
        return new Card(cardName, false);
    }

    public void addInvokeCost(ICost invokeCost) {
        this.invokeCost = invokeCost;
    }

}

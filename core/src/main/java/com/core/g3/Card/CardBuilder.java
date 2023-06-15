package com.core.g3.Card;

import com.core.g3.Card.Type.CardTypeBuilder;
import com.core.g3.Commons.Amount;

public class CardBuilder {

    private CardName cardName;
    public final InvocationCostBuilder invocationCost;
    public CardTypeBuilder cardTypeBuilder;
    private Amount summonableSpace;
    private Amount price;
    private boolean shouldCount = false;

    public CardBuilder(CardName cardName) {
        this.cardName = cardName;
        this.invocationCost = new InvocationCostBuilder();
        this.cardTypeBuilder = new CardTypeBuilder();
        this.summonableSpace = new Amount(1);
        this.price = new Amount(1);
    }

    public Card build() {
        return new Card(cardName, this.shouldCount, this.invocationCost.getCost(), this.cardTypeBuilder.getTypes(),
                this.summonableSpace, this.price);
    }

    public void setShouldCount(boolean shouldCount) {
        this.shouldCount = shouldCount;
    }

    public void setSummonableSpace(Amount summonableSpace) {
        this.summonableSpace = summonableSpace;
    }

    public void setPrice(Amount price) {
        this.price = price;
    }

}

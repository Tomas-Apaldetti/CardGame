package com.core.g3.Card;

import com.core.g3.Card.Type.CardTypeBuilder;

public class CardBuilder {

    private CardName cardName;
    public final InvocationCostBuilder invocationCost;
    public CardTypeBuilder cardTypeBuilder;

    public CardBuilder(CardName cardName) {
        this.cardName = cardName;
        this.invocationCost = new InvocationCostBuilder();
        this.cardTypeBuilder = new CardTypeBuilder();
    }

    public Card build() {
        return new Card(cardName, false, this.invocationCost.getCost(), this.cardTypeBuilder.getTypes());
    }

}

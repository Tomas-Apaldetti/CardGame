package com.core.g3.Card;

public class CardBuilder {
    private CardType cardName;

    public final InvocationCostBuilder InvocationCost;

    public CardBuilder(CardType cardName) {
        this.cardName = cardName;
        this.InvocationCost = new InvocationCostBuilder();
    }

    public Card build() {
        return new Card(cardName, false, this.InvocationCost.getCost());
    }

}

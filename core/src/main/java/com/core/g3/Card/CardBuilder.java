package com.core.g3.Card;

import com.core.g3.Card.Type.CardTypeBuilder;

public class CardBuilder {

    private CardName cardName;
    public final InvocationCostBuilder invocationCost;
    private CardTypeBuilder cardTypeBuilder;

    public CardBuilder(CardName cardName) {
        this.cardName = cardName;
        this.invocationCost = new InvocationCostBuilder();
        this.cardTypeBuilder = new CardTypeBuilder();
    }

    public Card build() {
        return new Card(cardName, false, this.invocationCost.getCost(), this.cardTypeBuilder.getTypes());
    }

    public void setTypeCriatura() {
        this.cardTypeBuilder.setTypeCriatura();
    }

    public void setTypeArtefacto() {
        this.cardTypeBuilder.setTypeArtefacto();
    }

    public void setTypeAccion() {
        this.cardTypeBuilder.setTypeAccion();
    }

    public void setTypeReaccion() {
        this.cardTypeBuilder.setTypeReaccion();
    }

    public void setTypeCombinada() {
        this.cardTypeBuilder.setTypeCombinada();
    }

    public void setAllTypes() {
        this.cardTypeBuilder.setTypeCriatura();
        this.cardTypeBuilder.setTypeArtefacto();
        this.cardTypeBuilder.setTypeAccion();
        this.cardTypeBuilder.setTypeReaccion();
        this.cardTypeBuilder.setTypeCombinada();
    }

}

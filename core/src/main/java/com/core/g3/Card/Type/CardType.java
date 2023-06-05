package com.core.g3.Card.Type;

public class CardType implements ICardType {

    protected ICardType.CardType type;

    @Override
    public ICardType.CardType getType() {
        return this.type;
    }

}

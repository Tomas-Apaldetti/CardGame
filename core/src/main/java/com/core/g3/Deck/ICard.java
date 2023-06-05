package com.core.g3.Deck;

import com.core.g3.Card.CardType;

public interface ICard {

    public CardType getType();

    public boolean shouldCountAgainstTypeLimit();

}

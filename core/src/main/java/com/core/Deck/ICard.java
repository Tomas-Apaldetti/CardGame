package com.core.Deck;

import com.core.Card.CardName;

public interface ICard {

    public CardName getType();

    public boolean shouldCountAgainstTypeLimit();

}

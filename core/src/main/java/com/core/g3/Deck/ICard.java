package com.core.g3.Deck;

import com.core.g3.Card.CardName;

public interface ICard {

    public CardName getName();

    public boolean shouldCountAgainstNameLimit();

}

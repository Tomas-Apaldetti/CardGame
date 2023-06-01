package com.Intercambiables.core.Deck;

import com.Intercambiables.core.Card.CardType;

public interface ICard {

    public CardType getType();

    public boolean shouldCountAgainstTypeLimit();

}

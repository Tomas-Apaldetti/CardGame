package com.Intercambiables.core.Deck;

import com.Intercambiables.core.Card.CardName;

public interface ICard {

    public CardName getType();

    public boolean shouldCountAgainstTypeLimit();

}

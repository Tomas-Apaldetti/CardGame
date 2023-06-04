package com.Intercambiables.core.Deck;

import com.Intercambiables.core.GameDriver.DriverCardName;

public interface ICard {

    public DriverCardName getType();

    public boolean shouldCountAgainstTypeLimit();

}

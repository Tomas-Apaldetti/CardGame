package com.Intercambiables.core.Deck;

import com.Intercambiables.core.driver.DriverCardName;

public interface ICard {

    public DriverCardName getType();

    public boolean shouldCountAgainstTypeLimit();

}

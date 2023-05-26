package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;

public interface IBuyer {

    void addItem(ITransactionable item);

    void subtract(Amount value);

    boolean hasEnoughFounds(Amount value);
}

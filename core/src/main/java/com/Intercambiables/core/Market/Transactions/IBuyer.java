package com.Intercambiables.core.Market.Transactions;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Commons.Amount;

public interface IBuyer {

    void addItem(Card card);

    void subtract(Amount value);

    boolean hasEnoughFounds(Amount value);
}

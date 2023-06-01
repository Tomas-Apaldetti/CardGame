package com.Intercambiables.core.Market.Transactions;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Market.Amount;

public interface ISeller {

    void removeItem(Card card);

    void credit(Amount value);
}

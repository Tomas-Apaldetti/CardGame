package com.Intercambiables.core.Market;

import com.Intercambiables.core.Card.Card;

public interface ISeller {

    void removeItem(ITransactionable item);

    void credit(Amount value);
}

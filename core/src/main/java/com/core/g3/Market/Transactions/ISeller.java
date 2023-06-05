package com.core.g3.Market.Transactions;

import com.core.g3.Card.Card;
import com.core.g3.Commons.Amount;

public interface ISeller {

    void removeItem(Card card);

    void credit(Amount value);
}

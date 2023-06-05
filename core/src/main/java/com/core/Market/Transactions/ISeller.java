package com.core.Market.Transactions;

import com.core.Card.Card;
import com.core.Commons.Amount;

public interface ISeller {

    void removeItem(Card card);

    void credit(Amount value);
}

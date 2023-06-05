package com.core.g3.Market.Transactions;

import com.core.g3.Card.Card;
import com.core.g3.Commons.Amount;

public interface IBuyer {

    void addItem(Card card);

    void subtract(Amount value);

    boolean hasEnoughFounds(Amount value);
}

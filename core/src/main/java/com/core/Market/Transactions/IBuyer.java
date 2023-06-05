package com.core.Market.Transactions;

import com.core.Card.Card;
import com.core.Commons.Amount;

public interface IBuyer {

    void addItem(Card card);

    void subtract(Amount value);

    boolean hasEnoughFounds(Amount value);
}

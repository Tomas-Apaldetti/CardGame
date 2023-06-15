package com.core.g3.Market.Transactions;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;

public interface IBuyer {

    void addItem(Card card);

    void subtract(Amount value);

    boolean hasEnoughFounds(Amount value);

    void buyCards(CardName from, int amount);
}

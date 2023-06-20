package com.core.g3.Market.Transactions;

import com.core.g3.Card.CardName;

public interface ITransactionable {

  void addTo(IBuyer buyer);

  void removeFrom(ISeller seller);

  CardName getName();

}

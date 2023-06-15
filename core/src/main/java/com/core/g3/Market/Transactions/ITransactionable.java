package com.core.g3.Market.Transactions;

public interface ITransactionable {

  void addTo(IBuyer buyer);

  void removeFrom(ISeller seller);
}

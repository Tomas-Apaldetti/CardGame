package com.core.Market.Transactions;

public interface ITransactionable {

  void addTo(IBuyer buyer);

  void removeFrom(ISeller seller);
}

package com.Intercambiables.core.Market;

import com.Intercambiables.core.Commons.Amount;

public interface IWallet {
    void add(int value);

    void subtract(int value);

    boolean hasEnoughFounds(Amount value);

    int money();
}

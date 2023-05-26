package com.Intercambiables.core.Market;

import java.util.Collection;
import java.util.Currency;

public interface IWallet {
    void add(int value);
    void subtract(int value);

    boolean hasEnoughFounds(Amount value);
    int money();
}

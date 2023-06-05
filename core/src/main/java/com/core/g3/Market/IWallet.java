package com.core.g3.Market;

import com.core.g3.Commons.Amount;

public interface IWallet {
    void add(int value);

    void subtract(int value);

    boolean hasEnoughFounds(Amount value);

    int money();
}

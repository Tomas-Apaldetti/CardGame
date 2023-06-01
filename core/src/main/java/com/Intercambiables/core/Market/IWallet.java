package com.Intercambiables.core.Market;

public interface IWallet {
    void add(int value);

    void subtract(int value);

    boolean hasEnoughFounds(Amount value);

    int money();
}

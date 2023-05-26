package com.Intercambiables.core.Market;

import java.util.Collection;
import java.util.Currency;

public interface IWallet {
    void addCurrency(Amount value);
    void substractCurrency(Amount value);
}

package com.Intercambiables.core.Market;

import com.Intercambiables.core.Market.Exception.InvalidAmountException;

public class Amount {
    int value;
    public Amount(int initialValue){
        if (initialValue < 0){
            throw new InvalidAmountException();
        }
        this.value = initialValue;
    }
}

package com.Intercambiables.core.Market;

import com.Intercambiables.core.Market.Exception.InvalidAmountException;

public class Amount {
    private int value;
    public Amount(int initialValue){
        this.assertPositive(initialValue);
        this.value = initialValue;
    }
    private void assertPositive(int value){
        if (value < 0){
            throw new InvalidAmountException();
        }
    }
    public void add(int value){
        this.assertPositive(value);
        this.value += value;
    }
    public void subtract(int value){
        this.assertPositive(value);
        this.assertPositive(this.value - value);
        this.value -= value;
    }

    public int value(){
        return this.value;
    }
}

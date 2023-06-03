package com.Intercambiables.core.Commons;

import com.Intercambiables.core.Commons.Exception.InvalidAmountException;

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

    public void add(Amount value){
        this.value += value.value;
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

    public void subtract(Amount value){
        this.assertPositive(this.value - value.value);
        this.value -= value.value;
    }

    public void subtractOrZero(Amount other){
        this.value = Math.max(this.value - other.value(), 0);
    }

    public boolean gte(Amount other){
        return this.value >= other.value();
    }

    public int value(){
        return this.value;
    }
}

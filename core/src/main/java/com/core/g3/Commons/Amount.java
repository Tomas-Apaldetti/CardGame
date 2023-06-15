package com.core.g3.Commons;

import com.core.g3.Commons.Exception.InvalidAmountException;

public class Amount {
    private int value;

    public Amount(int initialValue) {
        this.assertPositive(initialValue);
        this.value = initialValue;
    }

    public Amount(Amount current) {
        this.value = current.value;
    }

    private void assertPositive(int value) {
        if (value < 0) {
            throw new InvalidAmountException();
        }
    }

    public void add(int value) {
        this.assertPositive(value);
        this.value += value;
    }

    public void add(Amount value) {
        this.assertPositive(value.value);
        this.value += value.value();
    }

    public void subtract(int value) {
        this.assertPositive(value);
        this.assertPositive(this.value - value);
        this.value -= value;
    }

    public void subtract(Amount amount) {
        this.assertPositive(amount.value);
        this.assertPositive(this.value - amount.value);
        this.value -= amount.value;
    }

    public boolean gt(Amount other) {
        return this.value > other.value;
    }

    public boolean gte(Amount other) {
        return this.value >= other.value();
    }

    public int value() {
        return this.value;
    }

    public void subtractOrZero(Amount value) {
        this.assertPositive(value.value);
        if (this.value - value.value() < 0) {
            this.value = 0;
        } else {
            this.value -= value.value();
        }
    }

    public void multiply(Amount increment) {
        this.value = this.value * increment.value;
    }
}

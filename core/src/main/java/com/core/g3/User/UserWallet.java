package com.core.g3.User;

import com.core.g3.Commons.Amount;
import com.core.g3.Market.Exception.InsufficientMoneyException;
import com.core.g3.Commons.Exception.InvalidAmountException;
import com.core.g3.Market.IWallet;

public class UserWallet implements IWallet {

    private final Amount amount;

    public UserWallet() {
        this.amount = new Amount(0);
    }

    public UserWallet(int initialValue) {
        this.amount = new Amount(initialValue);
    }

    @Override
    public void add(int value) {
        this.amount.add(value);
    }

    @Override
    public void subtract(int value) {
        try {
            this.amount.subtract(value);
        } catch (InvalidAmountException ex) {
            if (value < 0)
                throw ex;
            throw new InsufficientMoneyException(ex);
        }
    }

    @Override
    public boolean hasEnoughFounds(Amount value) {
        return this.amount.gte(value);
    }

    @Override
    public int money() {
        return this.amount.value();
    }

}

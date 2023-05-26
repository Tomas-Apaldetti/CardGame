package com.Intercambiables.core.User;

import com.Intercambiables.core.Market.Amount;
import com.Intercambiables.core.Market.Exception.InsufficientMoneyException;
import com.Intercambiables.core.Market.Exception.InvalidAmountException;
import com.Intercambiables.core.Market.IWallet;

public class UserWallet implements IWallet {

    private final Amount amount;

    public UserWallet(){
        this.amount = new Amount(0);
    }

    public UserWallet(int initialValue){
        this.amount = new Amount(initialValue);
    }

    @Override
    public void add(int value) {
        this.amount.add(value);
    }

    @Override
    public void subtract(int value) {
        try{
            this.amount.subtract(value);
        }catch (InvalidAmountException ex){
            if(value < 0) throw ex;
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

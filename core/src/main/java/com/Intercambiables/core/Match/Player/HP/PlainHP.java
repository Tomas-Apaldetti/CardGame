package com.Intercambiables.core.Match.Player.HP;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.Player.HP.Exception.HPAlreadyDepletedException;

public class PlainHP implements IHP{

    private Amount currentHP;

    public PlainHP(Amount value){
        this.currentHP = value;
    }
    @Override
    public IHP receiveDamage(Amount value) {
        if(this.isDepleted()){
            throw new HPAlreadyDepletedException();
        }
        this.currentHP.subtractOrZero(value);
        return this;
    }

    @Override
    public boolean isDepleted() {
        return this.currentHP.value() == 0;
    }

    @Override
    public int getNumeric() {
        return this.currentHP.value();
    }
}

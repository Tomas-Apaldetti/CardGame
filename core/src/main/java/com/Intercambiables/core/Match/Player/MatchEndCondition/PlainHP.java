package com.Intercambiables.core.Match.Player.MatchEndCondition;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.Player.MatchEndCondition.Exception.HPAlreadyDepletedException;

public class PlainHP implements IMatchEndCondition {

    private Amount currentHP;

    public PlainHP(Amount value){
        this.currentHP = value;
    }

    @Override
    public IMatchEndCondition modify(Amount value) {
        if(this.isMet()){
            throw new HPAlreadyDepletedException();
        }
        this.currentHP.subtractOrZero(value);
        return this;
    }

    @Override
    public boolean isMet() {
        return this.currentHP.value() == 0;
    }

    @Override
    public int getNumeric() {
        return this.currentHP.value();
    }
}

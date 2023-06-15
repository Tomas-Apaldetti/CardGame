package com.core.g3.Match.Player.MatchEndCondition;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.MatchEndCondition.Exception.HPAlreadyDepletedException;

public class PlainHP implements IMatchEndCondition {

    private Amount currentHP;

    public PlainHP(Amount value) {
        this.currentHP = value;
    }

    @Override
    public IMatchEndCondition modify(Amount value) {
        if (this.isMet()) {
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

    @Override
    public void receiveAttack(Amount damage) {
        this.currentHP.subtractOrZero(damage);
    }

    @Override
    public void destroy() {
        this.currentHP.subtractOrZero(this.currentHP);
    }

    @Override
    public void heal(Amount heal) {
        this.currentHP.add(heal);
    }
}

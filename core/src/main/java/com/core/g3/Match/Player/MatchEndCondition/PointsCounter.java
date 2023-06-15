package com.core.g3.Match.Player.MatchEndCondition;

import com.core.g3.Commons.Amount;

public class PointsCounter implements IMatchEndCondition {

    private final Amount current;
    private final Amount upperLimit;

    public PointsCounter(Amount initial, Amount victoryMet) {
        this.upperLimit = victoryMet;
        this.current = initial;
    }

    @Override
    public IMatchEndCondition modify(Amount value) {
        this.current.add(value);
        return this;
    }

    @Override
    public boolean isAttackable(){
        return false;
    }

    @Override
    public boolean isMet() {
        return this.current.gte(upperLimit);
    }

    @Override
    public int getNumeric() {
        return this.current.value();
    }
}

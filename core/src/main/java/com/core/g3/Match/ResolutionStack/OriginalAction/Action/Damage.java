package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;

public class Damage implements IEffect{
    private final Amount value;
    private final IAttackable victim;

    public Damage(Amount value, IAttackable victim){
        this.value = value;
        this.victim = victim;
    }

    public void apply(){
        this.victim.receiveAttack(this.value);
    }
}

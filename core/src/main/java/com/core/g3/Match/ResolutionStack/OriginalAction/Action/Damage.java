package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;

import java.util.ArrayList;
import java.util.List;

public class Damage implements IEffect{
    private Amount value;
    private final List<IAttackable> victims;

    public Damage(Amount value, IAttackable victim){
        this.value = value;
        this.victims = new ArrayList<>();
        this.victims.add(victim);
    }

    public Damage(Amount value, List<IAttackable> victims){
        this.value = value;
        this.victims = victims;
    }

    public void apply(){
        this.victims.forEach((victim) -> victim.receiveAttack(this.value));
    }

    public void setMaxDamage(Amount value){
        if(this.value.gte(value)){
            return;
        }
        this.value = value;
    }
}

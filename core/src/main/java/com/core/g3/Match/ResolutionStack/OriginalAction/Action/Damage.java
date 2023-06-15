package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;

import java.util.ArrayList;
import java.util.List;

public class Damage implements IEffect {

    private Amount value;
    private final List<IAttackable> victims;

    public Damage(Amount value, IAttackable victim) {
        this.value = value;
        this.victims = new ArrayList<>();
        this.victims.add(victim);
    }

    public Damage(Amount value, List<IAttackable> victims) {
        this.value = value;
        this.victims = victims;
    }

    @Override
    public ArrayList<ILingeringEffect> apply() {
        this.victims.forEach((victim) -> victim.receiveAttack(this.value));
        return new ArrayList<>();
    }

    @Override
    public List<ILingeringEffect> apply(Integer times) {
        this.victims.forEach((victim) -> victim.receiveAttack(new Amount(this.value.value() * times)));
        return new ArrayList<>();
    }

    public void setMaxDamage(Amount value) {
        if (this.value.gte(value)) {
            return;
        }
        this.value = value;
    }
}

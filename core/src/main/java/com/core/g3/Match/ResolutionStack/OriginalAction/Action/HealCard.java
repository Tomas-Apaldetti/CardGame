package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;

public class HealCard implements IEffect {

    private final Amount amount;
    private final IAttackable affected;

    public HealCard(Amount healAmount, IAttackable affected) {
        this.amount = healAmount;
        this.affected = affected;
    }

    @Override
    public List<ILingeringEffect> apply() {
        this.affected.heal(this.amount);
        return new ArrayList<>();
    }

    @Override
    public List<ILingeringEffect> apply(Integer times) {
        this.affected.heal(new Amount(this.amount.value() * times));
        return new ArrayList<>();
    }
}

package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;

public class HealCard implements IEffect{


    private final Amount amount;
    private final IAttackable affected;

    public HealCard(Amount healAmount, IAttackable affected) {
        this.amount = healAmount;
        this.affected = affected;
    }

    @Override
    public void apply() {
        this.affected.heal(this.amount);
    }
}

package com.core.g3.Card.Attack;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class SimpleAttack implements IAttack {

    private Amount damage;

    public SimpleAttack(Amount damage) {
        this.damage = damage;
    }

    @Override
    public OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival) {
        action.setType(ActionType.Attack);
        action.addAttack(this.damage, victim);
        return action;
    }
}

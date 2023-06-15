package com.core.g3.Card.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.Damage;

import java.util.List;

public class DestroyAction implements IAction {

    private Amount damage;

    public DestroyAction() {
        this.damage = new Amount(Integer.MAX_VALUE);
    }

    @Override
    public OriginalAction apply(OriginalAction action, List<IAttackable> affected, Player user) {
        action.setType(ActionType.Action);
        action.addEffect(new Damage(this.damage, affected));
        return action;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.Action);
        action.addEffect(new Damage(this.damage, rival));
        return action;
    }
}

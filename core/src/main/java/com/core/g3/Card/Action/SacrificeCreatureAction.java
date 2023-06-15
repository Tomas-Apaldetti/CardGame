package com.core.g3.Card.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class SacrificeCreatureAction implements IAction {

    public SacrificeCreatureAction() {
    }

    @Override
    public OriginalAction apply(OriginalAction action, IAttackable creature, Player user, Player rival ) {
        action.setType(ActionType.Action);
        creature.destroy();
        return action;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
}

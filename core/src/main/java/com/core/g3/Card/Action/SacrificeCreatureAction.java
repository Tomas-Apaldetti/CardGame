package com.core.g3.Card.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public class SacrificeCreatureAction implements IAction {

    public SacrificeCreatureAction() {
    }
    
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

    @Override
    public OriginalAction apply(OriginalAction action, List<IAttackable> affected, Player user) {
        return null;
    }
}

package com.core.g3.Card.Action;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.StealEnergy;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class DrainEnergy implements IAction {

    private final Amount value;

    public DrainEnergy(Amount energy) {
        this.value = energy;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.Action);
        action.addEffect(new StealEnergy(this.value, rival));
        return action;
    }
}

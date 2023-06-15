package com.core.g3.Card.Action;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.StealEnergy;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public class DrainEnergyAction implements IAction {

    private final Amount value;

    public DrainEnergyAction(Amount energy) {
        this.value = energy;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.Action);
        action.addEffect(new StealEnergy(this.value, rival));
        return action;
    }

    @Override
    public OriginalAction apply(OriginalAction action, List<IAttackable> affected, Player user) {
        throw new ActionNotUsableException();
    }
}

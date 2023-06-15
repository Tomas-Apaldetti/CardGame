package com.core.g3.Card.Action;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.AddEnergy;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public class AddEnergyAction implements IAction {

    private final EnergyType type;
    private final Amount amount;

    public AddEnergyAction(EnergyType type, Amount value) {
        this.type = type;
        this.amount = value;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.Action);
        action.addEffect(new AddEnergy(this.type, this.amount, user));
        return action;
    }

    @Override
    public OriginalAction apply(OriginalAction action, List<IAttackable> affected, Player user) {
        throw new ActionNotUsableException();
    }

}

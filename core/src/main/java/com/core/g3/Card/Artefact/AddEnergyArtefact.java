package com.core.g3.Card.Artefact;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.AddEnergy;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class AddEnergyArtefact implements IArtefactEffect{
    private final EnergyType type;
    private final Amount amount;

    public AddEnergyArtefact(EnergyType type, Amount value){
        this.type = type;
        this.amount = value;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.ArtefactEffect);
        action.addEffect(new AddEnergy(this.type, this.amount, user));
        return action;
    }

}

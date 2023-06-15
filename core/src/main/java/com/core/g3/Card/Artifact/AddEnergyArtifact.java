package com.core.g3.Card.Artifact;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.AddEnergy;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class AddEnergyArtifact implements IArtifactEffect {
    private final EnergyType type;
    private final Amount amount;

    public AddEnergyArtifact(EnergyType type, Amount value) {
        this.type = type;
        this.amount = value;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.ArtifactEffect);
        action.addEffect(new AddEnergy(this.type, this.amount, user));
        return action;
    }

}

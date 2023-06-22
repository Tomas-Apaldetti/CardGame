package com.core.g3.Card.Artifact;

import com.core.g3.Card.Artifact.Exceptions.ArtifactNotUsableException;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.HealCard;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public class HealCardArtifact implements IArtifactEffect {

    private final Amount healAmount;

    public HealCardArtifact(Amount value) {
        this.healAmount = value;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        throw new ArtifactNotUsableException();
    }

    @Override
    public OriginalAction apply(OriginalAction action, List<IAffectable> affected, Player user, Player rival) {
        action.setType(ActionType.ArtifactEffect);
        action.addEffect(new HealCard(this.healAmount, affected.get(0)));
        return action;
    }
}

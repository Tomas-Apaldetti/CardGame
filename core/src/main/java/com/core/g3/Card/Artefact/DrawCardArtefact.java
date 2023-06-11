package com.core.g3.Card.Artefact;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.DrawCard;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class DrawCardArtefact implements IArtefactEffect{
    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.ArtefactEffect);
        action.addEffect(new DrawCard(new Amount(1), user));
        return action;
    }
}

package com.core.g3.Card.Artefact;

import com.core.g3.Card.Artefact.Exceptions.ArtefactNotUsableException;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.HealCard;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class HealCardArtefact implements IArtefactEffect{

    private final Amount healAmount;

    public HealCardArtefact(Amount value){
        this.healAmount = value;
    }
    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        throw new ArtefactNotUsableException();
    }

    @Override
    public OriginalAction apply(OriginalAction action, IAttackable affected, Player user, Player rival) {
        action.setType(ActionType.ArtefactEffect);
        action.addEffect(new HealCard(this.healAmount, affected));
        return action;
    }
}

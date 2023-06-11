package com.core.g3.Card.Artefact;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.Damage;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public class MassiveDamage implements IArtefactEffect {

    private final Amount value;
    private final Attribute attrFilter;

    public MassiveDamage(Amount value, Attribute attrFilter){
        this.value = value;
        this.attrFilter = attrFilter;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.ArtefactEffect);
        List<IAttackable> creatures = rival.getCreatures(this.attrFilter);
        action.addEffect(new Damage(this.value, creatures));
        return action;
    }
}

package com.core.g3.Card.Action;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.Damage;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.Arrays;
import java.util.List;

public class BothMassiveDamageAction implements IAction {

    private final Amount value;
    private final Attribute attrFilter;

    public BothMassiveDamageAction(Amount value, Attribute attrFilter) {
        this.value = value;
        this.attrFilter = attrFilter;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.Action);
        List<IAttackable> userCreatures = user.getCreatures(this.attrFilter);
        List<IAttackable> rivalCreatures = rival.getCreatures(this.attrFilter);
        action.addEffect(new Damage(this.value, (IAttackable) Arrays.asList(userCreatures, rivalCreatures)));
        return action;
    }

    @Override
    public OriginalAction apply(OriginalAction action, List<IAttackable> affected, Player user) {
        throw new ActionNotUsableException();
    }
}

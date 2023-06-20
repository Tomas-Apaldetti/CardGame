package com.core.g3.Card.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.Damage;

import java.util.List;
import java.util.stream.Collectors;

public class DamageAction implements IAction {

    private final Amount damage;

    public DamageAction(Amount damage) {
        this.damage = damage;
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.Action);
        action.addEffect(new Damage(damage, rival));
        return action;
    }

    @Override
    public OriginalAction apply(OriginalAction action, List<IAffectable> affected, Player user, Player rival) {
        action.setType(ActionType.Action);

        action.addEffect(new Damage(
                damage,
                affected.stream().map(a -> (IAttackable) a).collect(Collectors.toList())
        ));
        return action;
    }
}

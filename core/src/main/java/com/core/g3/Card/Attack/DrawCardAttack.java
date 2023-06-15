package com.core.g3.Card.Attack;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.DrawCard;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.Optional;

public class DrawCardAttack implements IAttack {

    private final Optional<IAttack> next;

    public DrawCardAttack() {
        this.next = Optional.empty();
    }

    public DrawCardAttack(IAttack next) {
        this.next = Optional.ofNullable(next);
    }

    @Override
    public OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival) {
        if (this.next.isPresent()) {
            this.next.get().attack(action, victim, user, rival);
        }
        action.setType(ActionType.Attack);
        action.addEffect(new DrawCard(new Amount(1), user));
        return action;
    }

}

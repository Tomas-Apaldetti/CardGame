package com.core.g3.Card.Action;

import java.util.Optional;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.DrawCard;

import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class DrawCardAction implements IAction {

    private Amount value;

    public DrawCardAction(Optional<Amount> value) {
        this.value = value.orElse(new Amount(1));
    }

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        action.setType(ActionType.Action);
        action.addEffect(new DrawCard(this.value, user));
        return action;
    }
}

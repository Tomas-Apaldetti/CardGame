package com.core.g3.Card.Action;

import com.core.g3.Card.Card;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.StealEnergy;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public class SacrificeCreatureAction implements IAction {

    private final Amount increment;

    public SacrificeCreatureAction(Amount increment) {
        this.increment = increment;
    }

    @Override
    public OriginalAction apply(OriginalAction action, List<IAffectable> affected, Player user, Player rival) {
        action.setType(ActionType.Action);
        Card creature = (Card) affected.get(0);
        CardInGame cig = user.getCardInGame(creature);
        cig.destroy();

        Amount cost = new Amount(creature.getInvocationCost().current());
        cost.multiply(this.increment);
        action.addEffect(new StealEnergy(cost, rival));
        return action;
    }
}

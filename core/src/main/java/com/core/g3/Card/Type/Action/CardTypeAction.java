package com.core.g3.Card.Type.Action;

import java.util.List;

import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class CardTypeAction extends CardType {

    private final ICost useCost;
    private final IAction effect;

    public CardTypeAction(IAction effect) {
        super(CardTypeName.Action);
        this.useCost = new NullInvocationCost();
        this.effect = effect;
    }

    public CardTypeAction(ICost useCost, IAction effect) {
        super(CardTypeName.Action);
        this.useCost = useCost;
        this.effect = effect;
    }

    @Override
    public boolean is(CardTypeName cardType) {
        return CardTypeName.Action == cardType;
    }

    @Override
    public OriginalAction action(OriginalAction action, Player user, Player rival) {
        useCost.apply(user);
        return this.effect.apply(action, user, rival);
    }

    @Override
    public OriginalAction action(OriginalAction action, IAttackable affected, Player user, Player rival) {
        return this.effect.apply(action, affected, user, rival);
    }
}

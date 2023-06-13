package com.core.g3.Card.Type.Action;

import java.util.List;

import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public class CardTypeAction extends CardType {

    private final ICost useCost;
    private final IAction effect;

    public CardTypeAction(IAction effect) {
        super(ICardType.CardType.Action);
        this.useCost = new NullInvocationCost();
        this.effect = effect;
    }

    public CardTypeAction(ICost useCost, IAction effect) {
        super(ICardType.CardType.Action);
        this.useCost = useCost;
        this.effect = effect;
    }

    @Override
    public boolean isAction() {
        return true;
    }

    @Override
    public ICost getEnergyCost() {
        return this.useCost;
    }

    @Override
    public OriginalAction action(OriginalAction action, Player user, Player rival) {
        return this.effect.apply(action, user, rival);
    }

    @Override
    public OriginalAction action(OriginalAction action, List<IAttackable> victims, Player user, Player rival) {
        return this.effect.apply(action, victims, user, rival);
    }
}

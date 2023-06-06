package com.core.g3.Card.Type.Action;

import java.util.List;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;

public class CardTypeAction extends CardType {

    private final ICost useCost;
    private final List<IEffect> effects;

    public CardTypeAction(List<IEffect> effects) {
        this.type = ICardType.CardType.Action;
        this.useCost = new NullInvocationCost();
        this.effects = effects;
    }

    public CardTypeAction(ICost useCost, List<IEffect> effects) {
        this.type = ICardType.CardType.Action;
        this.useCost = useCost;
        this.effects = effects;
    }

}

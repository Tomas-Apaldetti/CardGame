package com.core.g3.Card.Type.Reaction;

import java.util.List;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;

public class CardTypeReaction extends CardType {

    private final ICost useCost;
    private final List<IEffect> effects;

    public CardTypeReaction(List<IEffect> effects) {
        this.type = ICardType.CardType.Reaction;
        this.useCost = new NullInvocationCost();
        this.effects = effects;
    }

    public CardTypeReaction(ICost useCost, List<IEffect> effects) {
        this.type = ICardType.CardType.Reaction;
        this.useCost = useCost;
        this.effects = effects;
    }

}
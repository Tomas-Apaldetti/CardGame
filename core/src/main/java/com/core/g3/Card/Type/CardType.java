package com.core.g3.Card.Type;

import java.util.List;

import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.Exceptions.CardTypeNeedsAtLeastOneEffectException;

public class CardType implements ICardType {

    protected ICardType.CardType type;

    @Override
    public ICardType.CardType getType() {
        return this.type;
    }

    protected void assertEffects(List<IEffect> effects) {
        if (effects.isEmpty()) {
            throw new CardTypeNeedsAtLeastOneEffectException();
        }
    }
}

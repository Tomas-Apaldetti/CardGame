package com.core.g3.Card.Type;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.Exceptions.CardTypeNeedsAtLeastOneEffectException;
import com.core.g3.Match.Zone.ActiveZoneType;

public abstract class CardType implements ICardType {

    protected ICardType.CardType type;
    protected List<ActiveZoneType> allowedZones;

    protected CardType(CardType type) {
        this.type = type;
        this.allowedZones = new ArrayList<>();
    }

    protected CardType(CardType type, List<ActiveZoneType> allowedZones) {
        this.type = type;
        this.allowedZones = allowedZones;
    }

    @Override
    public ICardType.CardType getType() {
        return this.type;
    }

    protected void assertEffects(List<IEffect> effects) {
        if (effects.isEmpty()) {
            throw new CardTypeNeedsAtLeastOneEffectException();
        }
    }

    @Override
    public boolean isSummonableIn(ActiveZoneType zoneType) {
        return this.allowedZones.contains(zoneType);
    }
}

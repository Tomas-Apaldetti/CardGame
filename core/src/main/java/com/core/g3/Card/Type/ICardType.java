package com.core.g3.Card.Type;

import com.core.g3.Match.Zone.ActiveZoneType;

public interface ICardType {
    public enum CardType {
        Creature,
        Artefact,
        Action,
        Reaction,
    }

    CardType getType();

    boolean isSummonableIn(ActiveZoneType zoneType);
}

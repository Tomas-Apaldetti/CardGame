package com.core.g3.Card.Type;

public interface ICardType {
    public enum CardType {
        Creature,
        Artefact,
        Action,
        Reaction,
    }

    CardType getType();
}

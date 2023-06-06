package com.core.g3.Card.Type;

public interface ICardType {
    public enum CardType {
        Creature,
        Artifact,
        Action,
        Reaction,
        Combined,
    }

    CardType getType();
}

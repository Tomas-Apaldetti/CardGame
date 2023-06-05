package com.core.g3.Card.Type;

public interface ICardType {
    public enum CardType {
        Criatura,
        Artefacto,
        Accion,
        Reaccion,
        Combinada,
    }

    CardType getType();
}

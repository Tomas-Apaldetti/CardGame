package com.core.g3.Match.DeckPlayable;

public class EmptyDeckPlayableException extends RuntimeException {
    public EmptyDeckPlayableException() {
        super("El mazo de cartas en juego está vacío");
    }
}

package com.core.g3.Match.Zone.Exceptions;

public class CardLimitReachedException extends RuntimeException {
    public CardLimitReachedException() {
        super("Se alcanzo el limite de cartas en esta zona");
    }
}

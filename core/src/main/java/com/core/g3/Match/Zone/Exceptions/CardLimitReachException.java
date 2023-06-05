package com.core.g3.Match.Zone.Exceptions;


public class CardLimitReachException extends RuntimeException {
    public CardLimitReachException() {
        super("Se alcanzo el limite de cartas en esta zona");
    }
}

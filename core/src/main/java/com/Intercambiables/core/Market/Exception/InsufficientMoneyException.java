package com.Intercambiables.core.Market.Exception;

public class InsufficientMoneyException extends RuntimeException {
    public InsufficientMoneyException() {
    }

    public InsufficientMoneyException(Throwable cause) {
        super(cause);
    }
}

package com.core.g3.Market.Exceptions;

public class InsufficientMoneyException extends RuntimeException {
    public InsufficientMoneyException() {
    }

    public InsufficientMoneyException(Throwable cause) {
        super(cause);
    }
}

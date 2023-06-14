package com.core.g3.Match.Phase.Exceptions;

import com.core.g3.Match.Phase.IPhase.PhaseType;

public class NotPossibleToSummonInPhase extends RuntimeException {
    public NotPossibleToSummonInPhase(PhaseType phase) {
        super("No es posible invocar en la fase " + phase.toString() + "");
    }
}

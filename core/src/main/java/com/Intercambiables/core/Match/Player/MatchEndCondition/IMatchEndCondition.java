package com.Intercambiables.core.Match.Player.MatchEndCondition;

import com.Intercambiables.core.Commons.Amount;

public interface IMatchEndCondition {

    IMatchEndCondition modify(Amount value);

    boolean isMet();

    int getNumeric();
}

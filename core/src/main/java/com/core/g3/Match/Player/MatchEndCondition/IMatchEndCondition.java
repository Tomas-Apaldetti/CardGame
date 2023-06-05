package com.core.g3.Match.Player.MatchEndCondition;

import com.core.g3.Commons.Amount;

public interface IMatchEndCondition {

    IMatchEndCondition modify(Amount value);

    boolean isMet();

    int getNumeric();
}

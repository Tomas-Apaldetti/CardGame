package com.core.Match.Player.MatchEndCondition;

import com.core.Commons.Amount;

public interface IMatchEndCondition {

    IMatchEndCondition modify(Amount value);

    boolean isMet();

    int getNumeric();
}

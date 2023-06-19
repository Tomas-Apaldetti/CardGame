package com.core.g3.Match.Player.MatchEndCondition;

public interface IConditionMetPub {

    void addConditionMet(IConditionMetSub sub);

    void removeConditionMet(IConditionMetSub sub);
}

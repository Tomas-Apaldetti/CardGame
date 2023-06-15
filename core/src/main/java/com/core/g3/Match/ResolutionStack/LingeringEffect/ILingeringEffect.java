package com.core.g3.Match.ResolutionStack.LingeringEffect;

import com.core.g3.Match.Phase.AttackPhase;
import com.core.g3.Match.Phase.EndPhase;
import com.core.g3.Match.Phase.InitialPhase;
import com.core.g3.Match.Phase.MainPhase;
import com.core.g3.Match.Player.Player;

public interface ILingeringEffect {

    default void apply(InitialPhase current, Player activePlayer) {
        return;
    }

    default void apply(MainPhase current, Player activePlayer) {
        return;
    }

    default void apply(EndPhase current, Player activePlayer) {
        return;
    }

    default void apply(AttackPhase current, Player activePlayer) {
        return;
    }
}
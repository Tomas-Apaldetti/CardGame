package com.core.g3.Match.ResolutionStack.LingeringEffect;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Phase.InitialPhase;
import com.core.g3.Match.Player.Player;

public class OnInitialDrain implements ILingeringEffect {

    private Player victim;
    private Amount amount;

    public OnInitialDrain(Amount value, Player toWhom) {
        this.victim = toWhom;
        this.amount = value;
    }

    public void apply(InitialPhase current, Player activePlayer) {
        if (activePlayer.equals(this.victim)) {
            this.victim.consumeMax(amount);
        }
    }
}
package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;

public class StealEnergy implements IEffect {

    private final Amount value;
    private final Player user;

    public StealEnergy(Amount value, Player user) {
        this.value = value;
        this.user = user;
    }

    @Override
    public void apply() {
        this.user.consumeMax(this.value);
    }
}

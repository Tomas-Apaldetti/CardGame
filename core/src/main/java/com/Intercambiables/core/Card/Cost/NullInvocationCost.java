package com.Intercambiables.core.Card.Cost;

import com.Intercambiables.core.Match.Player.Player;

public class NullInvocationCost implements ICost{
    @Override
    public void apply(Player player) {
        return;
    }
}

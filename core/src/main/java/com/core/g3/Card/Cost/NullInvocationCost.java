package com.core.g3.Card.Cost;

import com.core.g3.Match.Player.Player;

public class NullInvocationCost implements ICost {
    @Override
    public void apply(Player player) {
        return;
    }
}

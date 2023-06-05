package com.core.Card.Cost;

import com.core.Match.Player.Player;

public class NullInvocationCost implements ICost {
    @Override
    public void apply(Player player) {
        return;
    }
}

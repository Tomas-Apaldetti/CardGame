package com.core.g3.Card.Cost;

import com.core.g3.Card.Cost.Exception.CostDoesNotHaveCountableValueException;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;

public interface ICost {

    default Amount current() {
        throw new CostDoesNotHaveCountableValueException();
    }

    void apply(Player player);
}

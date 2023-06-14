package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;

public class AddEnergy implements IEffect{

    private final EnergyType energyToAdd;
    private final Amount amountToAdd;
    private final Player user;

    public AddEnergy(EnergyType energyToAdd, Amount amountToAdd, Player user) {
        this.energyToAdd = energyToAdd;
        this.amountToAdd = amountToAdd;
        this.user = user;
    }

    @Override
    public void apply() {
        this.user.add(this.energyToAdd, this.amountToAdd);
    }
}

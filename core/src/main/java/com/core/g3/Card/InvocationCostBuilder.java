package com.core.g3.Card;

import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.HandDiscardCost;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

import java.util.Optional;

public class InvocationCostBuilder {

    private ICost currentCost;

    InvocationCostBuilder() {
        this.currentCost = new NullInvocationCost();
    }

    public InvocationCostBuilder addEnergyCost(Amount value) {
        this.currentCost = new CostEnergy(Optional.empty(), value, Optional.ofNullable(this.currentCost));
        return this;
    }

    public InvocationCostBuilder addEnergyCost(EnergyType type, Amount value) {
        this.currentCost = new CostEnergy(Optional.of(type), value, Optional.ofNullable(this.currentCost));
        return this;
    }

    public InvocationCostBuilder addHandDiscardCost() {
        this.currentCost = new HandDiscardCost(this.currentCost);
        return this;
    }

    public ICost getCost() {
        return this.currentCost;
    }
}

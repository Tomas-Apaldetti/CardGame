package com.Intercambiables.core.Card;

import com.Intercambiables.core.Card.Cost.CostEnergy;
import com.Intercambiables.core.Card.Cost.HandDiscardCost;
import com.Intercambiables.core.Card.Cost.ICost;
import com.Intercambiables.core.Card.Cost.NullInvocationCost;
import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.Player.Resources.EnergyType;

import java.util.Optional;

public class InvocationCostBuilder {

    private ICost currentCost;
    InvocationCostBuilder(){
        this.currentCost = new NullInvocationCost();
    }

    public InvocationCostBuilder addEnergyCost(Amount value){
        this.currentCost = new CostEnergy(Optional.empty(), value, Optional.ofNullable(this.currentCost));
        return this;
    }

    public InvocationCostBuilder addEnergyCost(EnergyType type, Amount value){
        this.currentCost = new CostEnergy(Optional.of(type), value, Optional.ofNullable(this.currentCost));
        return this;
    }

    public InvocationCostBuilder addHandDiscardCost(){
        this.currentCost = new HandDiscardCost(this.currentCost);
        return this;
    }

    public ICost getCost(){
        return this.currentCost;
    }
}

package com.Intercambiables.core.Card.Cost;

import java.util.Optional;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Commons.Exception.InvalidAmountException;
import com.Intercambiables.core.Match.Player.Player;
import com.Intercambiables.core.Match.Player.Resources.EnergyType;
import com.Intercambiables.core.Match.Player.Resources.IResource;

public class CostEnergy implements ICost {

    private Optional<EnergyType> energyType;
    private Amount cost;
    private Optional<ICost> next;

    public CostEnergy(Optional<EnergyType> energyType, Amount cost) {
        this.energyType = energyType;
        this.cost = cost;
        this.next = Optional.empty();
    }

    public CostEnergy(Optional<EnergyType> energyType, Amount cost, Optional<ICost> nextInvokeCost) {
        this.energyType = energyType;
        this.cost = cost;
        this.next = nextInvokeCost;
    }

    private void applyNext(Player player){
        if(this.next.isPresent()) {
            this.next.get().apply(player);
        }
    }

    @Override
    public void apply(Player player) {
        if(this.energyType.isEmpty()){
            this.applyNext(player);
            player.consume(this.energyType, this.cost);
            return;
        }

        IResource resource = player.consume(this.energyType, this.cost);

        try {
            this.applyNext(player);
        } catch (InvalidAmountException invalidAmountException) {
            player.add(resource, this.cost);
            throw invalidAmountException;
        }

    }
}

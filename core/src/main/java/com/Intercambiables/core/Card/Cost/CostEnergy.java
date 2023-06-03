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
    private ICost next;

    public CostEnergy(Optional<EnergyType> energyType, Amount cost) {
        this.energyType = energyType;
        this.cost = cost;
        this.next = null;
    }

    public CostEnergy(Optional<EnergyType> energyType, Amount cost, ICost nextInvokeCost) {
        this.energyType = energyType;
        this.cost = cost;
        this.next = nextInvokeCost;
    }

    @Override
    public void apply(Player player) {
        IResource resource = player.consume(this.energyType, this.cost);

        try {
            this.next.apply(player);
        } catch (InvalidAmountException invalidAmountException) {
            player.add(resource, this.cost);
            throw invalidAmountException;
        }
    }
}

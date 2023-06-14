package com.core.g3.Card.Cost;

import com.core.g3.Card.Cost.Exception.CanNotPayException;
import com.core.g3.Commons.Amount;
import com.core.g3.Commons.Exception.InvalidAmountException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IResource;

import java.util.Optional;

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

    private void applyNext(Player player) {
        if (this.next.isPresent()) {
            this.next.get().apply(player);
        }
    }

    private IResource applyThis(Player player) {
        try {
            return player.consume(this.energyType, this.cost);
        } catch (InvalidAmountException e) {
            throw new CanNotPayException();
        }
    }

    @Override
    public void apply(Player player) {
        if (this.energyType.isEmpty()) {
            this.applyNext(player);
            this.applyThis(player);
            return;
        }

        IResource resource = this.applyThis(player);

        try {
            this.applyNext(player);
        } catch (CanNotPayException e) {
            player.add(resource, this.cost);
            throw e;
        }

    }
}

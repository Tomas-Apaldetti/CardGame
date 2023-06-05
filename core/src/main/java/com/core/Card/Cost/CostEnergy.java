package com.core.Card.Cost;

import java.util.Optional;

import com.core.Card.Cost.Exception.CanNotPayException;
import com.core.Commons.Amount;
import com.core.Commons.Exception.InvalidAmountException;
import com.core.Match.Player.Player;
import com.core.Match.Player.Resources.EnergyType;
import com.core.Match.Player.Resources.IResource;

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

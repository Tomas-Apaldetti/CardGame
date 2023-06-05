package com.core.Match.Player;

import com.core.Commons.Amount;
import com.core.Commons.Exception.InvalidAmountException;
import com.core.Match.Player.Exception.InvalidEnergyTypeException;
import com.core.Match.Player.Resources.Energy;
import com.core.Match.Player.Resources.EnergyType;
import com.core.Match.Player.Resources.IModifiableResource;
import com.core.Match.Player.Resources.IResource;

import java.util.*;

public class PlayerEnergies {

    private Set<IModifiableResource> energies;

    public PlayerEnergies() {
        this.energies = new HashSet<>();
        this.energies.add(new Energy(EnergyType.Water, new Amount(0)));
        this.energies.add(new Energy(EnergyType.Fire, new Amount(0)));
        this.energies.add(new Energy(EnergyType.Plant, new Amount(0)));
    }

    public IModifiableResource getEnergy(EnergyType energyType) {
        Optional<IModifiableResource> energy = this.energies.stream()
                .filter(e -> e.equals(energyType))
                .findFirst();
        if (energy.isPresent()) {
            return energy.get();
        }
        throw new InvalidEnergyTypeException();
    }

    public IModifiableResource getEnergy(IResource resource) {
        Optional<IModifiableResource> energy = this.energies.stream()
                .filter(e -> e.equals(resource))
                .findFirst();
        if (energy.isPresent()) {
            return energy.get();
        }
        throw new InvalidEnergyTypeException();
    }

    public Collection<IModifiableResource> getEnergies() {
        return this.energies.stream().toList();
    }

    public void add(EnergyType type, Amount value) {
        this.getEnergy(type).add(value);
    }

    public void add(IResource resource, Amount value) {
        this.getEnergy(resource).add(value);
    }

    public IResource consume(EnergyType type, Amount value) {
        return this.getEnergy(type).consume(value);
    }

    public IResource consumeAny(Amount value) {
        List<IModifiableResource> a = this.energies.stream().toList();
        for (IModifiableResource energy : a) {
            if (this.tryConsume(energy, value)) {
                return energy;
            }
        }
        throw new InvalidAmountException();
    }

    private boolean tryConsume(IModifiableResource res, Amount value) {
        try {
            res.consume(value);
            return true;
        } catch (InvalidAmountException e) {
            return false;
        }
    }
}

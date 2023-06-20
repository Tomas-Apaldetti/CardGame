package com.core.g3.Match.Player;

import com.core.g3.Commons.Amount;
import com.core.g3.Commons.Exception.InvalidAmountException;
import com.core.g3.Match.Player.Exception.InvalidEnergyTypeException;
import com.core.g3.Match.Player.Resources.Energy;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IModifiableResource;
import com.core.g3.Match.Player.Resources.IResource;

import java.util.*;

public class PlayerEnergies {

    private Set<IModifiableResource> energies;

    public PlayerEnergies() {
        this.energies = new HashSet<>();
        for (EnergyType type: EnergyType.values()){
            this.energies.add(new Energy(type, new Amount(0)));
        }
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

    public Optional<EnergyType> getMaxType() {
        IModifiableResource max = this.energies.stream().reduce((x, y) -> x.available() > y.available() ? x : y).get();
        return Optional.ofNullable(((Energy) max).getType());
    }
}

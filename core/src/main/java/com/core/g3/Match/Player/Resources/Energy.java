package com.core.g3.Match.Player.Resources;

import java.util.Objects;

import com.core.g3.Commons.Amount;

public class Energy implements IModifiableResource {

    private final Amount currentAmount;
    private final EnergyType type;

    public Energy(EnergyType type, Amount initialValue) {
        this.currentAmount = initialValue;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        boolean sameOrEnergyType = this.getClass() == o.getClass() || o.getClass() == EnergyType.class;
        if (o == null || !sameOrEnergyType)
            return false;
        if (o.getClass() == EnergyType.class) {
            EnergyType type = (EnergyType) o;
            return this.type == type;
        }
        Energy energy = (Energy) o;
        return type == energy.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public void add(Amount value) {
        this.currentAmount.add(value);
    }

    @Override
    public IResource consume(Amount value) {
        this.currentAmount.subtract(value);
        return this;
    }

    @Override
    public int available() {
        return this.currentAmount.value();
    }
}

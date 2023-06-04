package com.Intercambiables.core.Match.Player.Resources;

import com.Intercambiables.core.Commons.Amount;

import java.util.Objects;

public class Energy implements IModifiableResource{

    private final Amount currentAmount;
    private final EnergyType type;
    public Energy(EnergyType type, Amount initialValue){
        this.currentAmount = initialValue;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        boolean sameOrEnergyType = this.getClass() == o.getClass() || o.getClass() == EnergyType.class;
        if (o == null || !sameOrEnergyType) return false;
        if (o.getClass() == EnergyType.class){
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
    public void consume(Amount value) {
        this.currentAmount.subtract(value);
    }

    @Override
    public int available() {
        return this.currentAmount.value();
    }
}
